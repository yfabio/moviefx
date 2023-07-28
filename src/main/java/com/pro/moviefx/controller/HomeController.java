package com.pro.moviefx.controller;

import java.net.URL;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.pro.moviefx.api.Media;
import com.pro.moviefx.api.Movie;
import com.pro.moviefx.api.Movies;
import com.pro.moviefx.api.Tv;
import com.pro.moviefx.api.Tvs;
import com.pro.moviefx.fx.CallbackController;
import com.pro.moviefx.fx.FxLoader;
import com.pro.moviefx.fx.FxLoader.Url;
import com.pro.moviefx.http.Http;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		accordionFilter.setExpandedPane(titlePaneFilter);

		new Thread(new TaskBuilder<List<Node>>().call(() -> {
			String json = Http.get("https://api.themoviedb.org/3/movie/popular?page=%d".formatted(nextInt.get()),
					BodyHandlers.ofString(), headers);
			return getMoviesCards(json);
		}).succeeded(cards -> {
			flowpane.getChildren().addAll(cards);
		}).build()).start();

		loadmore.setOnAction(evt -> onLoadMore());

	}

	private void onLoadMore() {

		new Thread(new TaskBuilder<List<Node>>()
			.scheduled(flowpane.getChildren()::clear)	
			.call(() -> {			
				if(whichDoILoadMore.get(0).equalsIgnoreCase("movies")) {
					String json = Http.get(
							"https://api.themoviedb.org/3/movie/popular?page=%d".formatted(nextInt.incrementAndGet()),
							BodyHandlers.ofString(), headers);
					return getMoviesCards(json);
				}else if(whichDoILoadMore.get(0).equalsIgnoreCase("tvShow")){
					String json = Http.get(
							"https://api.themoviedb.org/3/tv/popular?page=%d".formatted(nextInt.incrementAndGet()),
							BodyHandlers.ofString(), headers);
					return getTvsCards(json);
				}else {
					throw new RuntimeException();
				}			
		}).succeeded(cards -> {
			flowpane.getChildren().addAll(cards);
		}).build()).start();

	}

	private List<Node> getMoviesCards(String json) {

		List<Node> list = new ArrayList<>();

		try {

			Gson gson = new Gson();

			Movies movies = gson.fromJson(json, Movies.class);

			List<Movie> listMovies = movies.getResults();

			for (Movie movie : listMovies) {
				Pane pane = FxLoader.load(Url.CARD_MOVIES, () -> movie).navigate();
				list.add(pane);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private List<Node> getTvsCards(String json) {

		List<Node> list = new ArrayList<>();

		try {

			Gson gson = new Gson();

			Tvs tvs = gson.fromJson(json, Tvs.class);

			List<Tv> tvList = tvs.getResults();

			for (Tv tv : tvList) {
				Pane pane = FxLoader.load(Url.CARD_TVS, () -> tv).navigate();
				list.add(pane);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void publish(List<? extends Media> value) {

		new Thread(new TaskBuilder<List<Node>>().scheduled(() -> {
			flowpane.getChildren().clear();
			if (whichDoILoadMore.size() > 1) {
				whichDoILoadMore.remove(0);
			}
		}).call(() -> {
			List<Node> list = new ArrayList<>();
			for (Media common : value) {
				if (common instanceof Movie) {
					Pane pane = FxLoader.load(Url.CARD_MOVIES, () -> Movie.class.cast(common)).navigate();
					list.add(pane);
				} else if (common instanceof Tv) {
					Pane pane = FxLoader.load(Url.CARD_TVS, () -> Tv.class.cast(common)).navigate();
					list.add(pane);
				}
			}
			return list;
		}).succeeded(flowpane.getChildren()::addAll).build()).start();

	}

}
