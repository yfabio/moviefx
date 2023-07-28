package com.pro.moviefx.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.pro.moviefx.api.Movie;
import com.pro.moviefx.api.Tv;
import com.pro.moviefx.fx.ContextMenuBuilder;
import com.pro.moviefx.fx.PlaceHolder;
import com.pro.moviefx.service.NavigationService;
import com.pro.moviefx.service.impl.NavigationServiceImpl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.SVGPath;

public class MainController extends BaseController  {

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
	
	private ArrayList<ContextMenu> list = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		placeholder.getNavigation().bindBidirectional(navigation);
		
		navigation.set(navigationService.getNavigator(HomeController.class));
		
		logo.setOnMouseClicked(evt -> navigation.setValue(navigationService.getNavigator(HomeController.class)));

		btnMovies.setOnMouseEntered(evt -> onShowMoviesContextMenu(evt));
		btnTvShows.setOnMouseEntered(evt -> onShowTvShowsContexMenu(evt));
		btnPeople.setOnMouseEntered(evt -> onShowPeopleContexMenu(evt));
		btnMore.setOnMouseEntered(evt -> onShowMoreContexMenu(evt));
		
		whichDoILoadMore.add("movies");
		
	}

	private void onShowMoviesContextMenu(MouseEvent evt) {
		new ContextMenuBuilder(Button.class.cast(evt.getSource()))
		.addMenuItem("Popular","https://api.themoviedb.org/3/movie/popular",headers,Movie.class, items -> {
			navigation.set(navigationService.getNavigator(HomeController.class, () -> items));
			whichDoILoadMore.add("movies");
		}).addMenuItem("Now Playing","https://api.themoviedb.org/3/movie/now_playing", headers,Movie.class,items -> {				
			navigation.set(navigationService.getNavigator(HomeController.class, () -> items));		
			whichDoILoadMore.add("movies");
		}).addMenuItem("Upcoming","https://api.themoviedb.org/3/movie/upcoming", headers,Movie.class, items -> {
			navigation.set(navigationService.getNavigator(HomeController.class, () -> items));
			whichDoILoadMore.add("movies");
		}).addMenuItem("Top Rated","https://api.themoviedb.org/3/movie/top_rated", headers,Movie.class, items -> {
			navigation.set(navigationService.getNavigator(HomeController.class, () -> items));
			whichDoILoadMore.add("movies");
		}).closeContextMenu(this::hideContexMenuIfItWasShowing)		
		.show();
	}


	private void onShowTvShowsContexMenu(MouseEvent evt) {
		new ContextMenuBuilder(Button.class.cast(evt.getSource()))
		.addMenuItem("Popular","https://api.themoviedb.org/3/tv/popular", headers, Tv.class,items -> {
			navigation.set(navigationService.getNavigator(HomeController.class, () -> items));
			whichDoILoadMore.add("tvShow");
		}).addMenuItem("Airing","https://api.themoviedb.org/3/tv/airing_today", headers, Tv.class,items -> {
			navigation.set(navigationService.getNavigator(HomeController.class, () -> items));
			whichDoILoadMore.add("tvShow");
		}).addMenuItem("On Tv","https://api.themoviedb.org/3/tv/on_the_air", headers, Tv.class,items -> {
			navigation.set(navigationService.getNavigator(HomeController.class, () -> items));
			whichDoILoadMore.add("tvShow");
		}).addMenuItem("Top Rated","https://api.themoviedb.org/3/tv/top_rated", headers, Tv.class,items -> {
			navigation.set(navigationService.getNavigator(HomeController.class, () -> items));
			whichDoILoadMore.add("tvShow");
		}).closeContextMenu(this::hideContexMenuIfItWasShowing)	
		.show();

	}
	

	private void onShowPeopleContexMenu(MouseEvent evt) {
		new ContextMenuBuilder(Button.class.cast(evt.getSource()))
		.addMenuItem("Popular People","", headers,null, items -> {
			onMessage("Popular People");
		}).closeContextMenu(this::hideContexMenuIfItWasShowing)
		.show();
	}

	private void onShowMoreContexMenu(MouseEvent evt) {
		new ContextMenuBuilder(Button.class.cast(evt.getSource()))
		.addMenuItem("Discussions","", headers, null,items -> {
			onMessage("Discussions");
		}).addMenuItem("Leaderboard","", headers, null,items -> {
			onMessage("Leaderboard");
		}).addMenuItem("Support","", headers,null, items -> {
			onMessage("Support");
		}).addMenuItem("API","", headers,null, items -> {
			onMessage("API");
		}).closeContextMenu(this::hideContexMenuIfItWasShowing)
		.show();
		
	}

	
	
	private void onMessage(String value) {		
		System.out.println("%s was not done yet!".formatted(value));
	}
	
	
	
	public void hideContexMenuIfItWasShowing(ContextMenu contextMenu) {		
		list.add(contextMenu);
		if(list.size() > 1) {
			list.get(0).hide();
			list.remove(0);
		}
	}
	
	
	

}
