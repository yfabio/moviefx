package com.pro.moviefx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pro.moviefx.api.MovieApi;
import com.pro.moviefx.api.Movies;
import com.pro.moviefx.api.Tmdb;
import com.pro.moviefx.api.TvApi;
import com.pro.moviefx.api.Tvs;
import com.pro.moviefx.fx.CallbackController;
import com.pro.moviefx.fx.Url;
import com.pro.moviefx.service.MovieService;
import com.pro.moviefx.service.NavigationService;
import com.pro.moviefx.service.TvService;
import com.pro.moviefx.service.impl.MovieServiceImpl;
import com.pro.moviefx.service.impl.NavigationServiceImpl;
import com.pro.moviefx.service.impl.TvServiceImpl;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import javafx.util.Callback;

public class HomeController extends BaseController implements CallbackController<Tmdb> {

	@FXML
	Pagination pagination;

	@FXML
	private Button loadmore;

	@FXML
	private TitledPane titlePaneFilter;

	@FXML
	private Accordion accordionFilter;

	@FXML
	private ComboBox<String> sortComboBox;

	private NavigationService navigationService = new NavigationServiceImpl();

	private MovieService movieService = new MovieServiceImpl();

	private TvService tvService = new TvServiceImpl();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		accordionFilter.setExpandedPane(titlePaneFilter);
		sortComboBox.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> {

			String[] values = getContextMenuSelectionValue().split(",");
			pagination.setCurrentPageIndex(0);

			switch (values[0]) {
				case "movies" -> {
	
					task(() -> {
						try {
							Callback<Integer, Node> callback = pageIndex -> createPageIndex(movieService.getMoviesSortedBy(
									MovieApi.valueOf(values[1]).getValue(n), pageIndex == 0 ? 1 : pageIndex));
							return callback;
						} catch (Exception e) {
							HomeController.this.alertError(e);
						}
						return null;
					}, pagination::setPageFactory);
	
				}
				case "tvs" -> {
	
					task(() -> {
						try {
							Callback<Integer, Node> callback = pageIndex -> createPageIndex(tvService
									.getTvsSortedBy(TvApi.valueOf(values[1]).getValue(n), pageIndex == 0 ? 1 : pageIndex));
							return callback;
						} catch (Exception e) {
							HomeController.this.alertError(e);
						}
						return null;
					}, pagination::setPageFactory);
	
				}
			}

		});
	}

	@Override
	public void accept(Tmdb tmdb) {
		createPage(tmdb);
	}

	private void createPage(Tmdb tmdb) {

		if (tmdb instanceof Movies movies) {
			pagination.setPageCount(movies.getTotal_pages());
			pagination.setCurrentPageIndex(0);

			setContextMenuSelectionValue(String.format("movies,%s", movies.getMovieApi().name()));

			task(() -> {
				try {
					Callback<Integer, Node> callback = pageIndex -> createPageIndex(
							movieService.getMovies(movies.getMovieApi(), pageIndex == 0 ? 1 : pageIndex));
					return callback;
				} catch (Exception e) {
					HomeController.this.alertError(e);
				}
				return null;
			}, pagination::setPageFactory);

		} else if (tmdb instanceof Tvs tvs) {
			pagination.setPageCount(tvs.getTotal_pages());
			pagination.setCurrentPageIndex(0);

			setContextMenuSelectionValue(String.format("tvs,%s", tvs.getTvApi().name()));

			task(() -> {
				try {
					Callback<Integer, Node> callback = pageIndex -> createPageIndex(
							tvService.getTvs(tvs.getTvApi(), pageIndex == 0 ? 1 : pageIndex));
					return callback;
				} catch (Exception e) {
					HomeController.this.alertError(e);
				}
				return null;
			}, pagination::setPageFactory);
		}

	}

	private Node createPageIndex(Tmdb tmdb) {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		FlowPane flowPane = new FlowPane();
		flowPane.setHgap(15);
		flowPane.setVgap(15);
		flowPane.setAlignment(Pos.TOP_CENTER);
		scrollPane.setPadding(new Insets(5));
		scrollPane.setContent(flowPane);

		if (tmdb instanceof Movies movies) {
			movies.getResults().forEach(movie -> {
				task(navigationService.loadView(Url.CARD_MOVIE, movie)::navigate, flowPane.getChildren()::add);
			});
		} else if (tmdb instanceof Tvs tvs) {
			tvs.getResults().forEach(tv -> {
				task(navigationService.loadView(Url.CARD_TV, tv)::navigate, flowPane.getChildren()::add);
			});
		}

		return scrollPane;
	}

}
