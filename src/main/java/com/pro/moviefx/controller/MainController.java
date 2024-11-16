package com.pro.moviefx.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.pro.moviefx.api.MovieApi;
import com.pro.moviefx.api.Movies;
import com.pro.moviefx.api.TvApi;
import com.pro.moviefx.api.Tvs;
import com.pro.moviefx.fx.PlaceHolder;
import com.pro.moviefx.fx.Url;
import com.pro.moviefx.service.MovieService;
import com.pro.moviefx.service.NavigationService;
import com.pro.moviefx.service.TvService;
import com.pro.moviefx.service.impl.MovieServiceImpl;
import com.pro.moviefx.service.impl.NavigationServiceImpl;
import com.pro.moviefx.service.impl.TvServiceImpl;
import com.pro.moviefx.task.Timeout;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;
import javafx.stage.Window;

public class MainController extends BaseController {

	@FXML
	private Button lang;

	@FXML
	private Button notifcation;

	@FXML
	private Button plus;

	@FXML
	private Button profile;

	@FXML
	private Button search;

	@FXML
	private PlaceHolder placeholder;

	@FXML
	private Button btnMore;

	@FXML
	private Button btnMovies;

	@FXML
	private Button btnPeople;

	@FXML
	private Button btnTvShows;

	@FXML
	private SVGPath logo;

	private NavigationService navigationService = new NavigationServiceImpl();

	private MovieService movieService = new MovieServiceImpl();

	private TvService tvService = new TvServiceImpl();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		placeholder.getNavigation().bindBidirectional(navigation);

		navigation.set(navigationService.getNavigator(Url.HOME));

		run(() -> movieService.getMovies(MovieApi.POPULAR), null,movies -> {			
			navigation.setValue(navigationService.getNavigator(Url.HOME,movies));
		});
		

		btnMovies.setOnMouseEntered(evt -> onShowContextMenu(evt));
		btnMovies.setOnMouseExited(evt -> hideContextMenu(evt));

		btnTvShows.setOnMouseEntered(evt -> onShowContextMenu(evt));
		btnTvShows.setOnMouseExited(evt -> hideContextMenu(evt));
				
		createMoviesContextMenu();
		createTvContextMenu();

	}

	private void hideContextMenu(MouseEvent evt) {
		ContextMenu contextMenu = Button.class.cast(evt.getSource()).getContextMenu();
		if (contextMenu != null && contextMenu.isShowing()) {
			Timeout.setTimeout(contextMenu::hide, 2000);
		}
	}

	private void onShowContextMenu(MouseEvent evt) {
		Button button = Button.class.cast(evt.getSource());
		ContextMenu contextMenu = button.getContextMenu();
		if (contextMenu != null && !contextMenu.isShowing()) {
			Scene scene = button.getScene();
			Window window = button.getScene().getWindow();
			Point2D coordination = button.localToScene(0.0, 0.0);
			Double x = 0.0, y = 0.0;

			x = coordination.getX();
			y = coordination.getY();
			x += scene.getX() + window.getX();
			y += scene.getY() + window.getY() + 28;
			contextMenu.show(button, x, y);
		}

	}

	

	private void createMoviesContextMenu() {

		ContextMenu contextMenu = new ContextMenu();

		contextMenu.setAutoHide(true);
		contextMenu.setAutoFix(true);

		Arrays.stream(MovieApi.values()).forEach(value -> {
			MenuItem menuItem = new MenuItem(value.getTitle());
			contextMenu.getItems().add(menuItem);
			menuItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Movies movies = movieService.getMovies(value);					
					navigation.set(navigationService.getNavigator(Url.HOME, movies));
				}
			});
		});

		btnMovies.setContextMenu(contextMenu);

	}

	private void createTvContextMenu() {

		ContextMenu contextMenu = new ContextMenu();
		contextMenu.setAutoHide(true);
		contextMenu.setAutoFix(true);

		Arrays.stream(TvApi.values()).forEach(value -> {
			MenuItem menuItem = new MenuItem(value.getTitle());
			contextMenu.getItems().add(menuItem);
			menuItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Tvs tvs = tvService.getTvs(value);
					tvs.setTvApi(value);
					navigation.set(navigationService.getNavigator(Url.HOME, tvs));
				}
			});
		});

		btnTvShows.setContextMenu(contextMenu);

	}

}
