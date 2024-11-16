package com.pro.moviefx.controller;

import java.net.URL;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.pro.moviefx.api.Media;
import com.pro.moviefx.api.Movie;
import com.pro.moviefx.api.Tv;
import com.pro.moviefx.fx.CallbackController;
import com.pro.moviefx.fx.Url;
import com.pro.moviefx.http.Http;
import com.pro.moviefx.service.MovieService;
import com.pro.moviefx.service.NavigationService;
import com.pro.moviefx.service.impl.MovieServiceImpl;
import com.pro.moviefx.service.impl.NavigationServiceImpl;
import com.pro.moviefx.task.TaskBuilder;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class HomeController extends BaseController implements CallbackController<List<? extends Media>> {

	@FXML
	private FlowPane flowpane;
	@FXML
	private Button loadmore;

	@FXML
	private TitledPane titlePaneFilter;

	@FXML
	private Accordion accordionFilter;

	private NavigationService navigationService = new NavigationServiceImpl();
	
	private MovieService movieService = new MovieServiceImpl();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		accordionFilter.setExpandedPane(titlePaneFilter);

		new Thread(new TaskBuilder<List<Node>>().call(() -> {
			String json = Http.get("https://api.themoviedb.org/3/movie/popular?page=%d".formatted(nextInt.get()),
					BodyHandlers.ofString());
			return movieService.getCardMovies(json);
		}).succeeded(cards -> {
			flowpane.getChildren().addAll(cards);
		}).build()).start();

	}


	

	@Override
	public void accept(List<? extends Media> value) {

		new Thread(new TaskBuilder<List<Node>>().call(() -> {
			List<Node> list = new ArrayList<>();
			for (Media common : value) {
				if (common instanceof Movie) {
					Pane pane = navigationService.getNavigator(Url.CARD_MOVIES, Movie.class.cast(common)).navigate();
					list.add(pane);
				} else if (common instanceof Tv) {
					Pane pane = navigationService.getNavigator(Url.CARD_TVS, Tv.class.cast(common)).navigate();
					list.add(pane);
				}
			}
			return list;
		}).succeeded(items -> {
			flowpane.getChildren().clear();
			flowpane.getChildren().addAll(items);
		}).build()).start();

	}

}
