package com.pro.moviefx.service.impl;

import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.pro.moviefx.api.MovieApi;
import com.pro.moviefx.api.Movies;
import com.pro.moviefx.fx.Url;
import com.pro.moviefx.http.Http;
import com.pro.moviefx.model.Media;
import com.pro.moviefx.model.Movie;
import com.pro.moviefx.service.MovieService;
import com.pro.moviefx.service.NavigationService;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class MovieServiceImpl implements MovieService {

	private static final String BASE_URL = "https://api.themoviedb.org/3/movie/"; 
	
	private NavigationService navigationService = new NavigationServiceImpl();
	
	private Gson gson = new Gson();
	
	@Override
	public List<? extends Media> getMoviesList(MovieApi movieApi) {
		
		String json = Http.get(String.format("%s%s",BASE_URL,movieApi.name().toLowerCase()), BodyHandlers.ofString());
		
		Movies movies = gson.fromJson(json, Movies.class);
		
		return movies.getResults();		
	
	}
	
	
	@Override
	public Movies getMovies(MovieApi movieApi) {
		String json = Http.get(String.format("%s%s",BASE_URL,movieApi.name().toLowerCase()), BodyHandlers.ofString());
		
		Movies movies = gson.fromJson(json, Movies.class);
		
		return movies;	
	}
	
	
	@Override
	public Movies getMovies(MovieApi movieApi, Integer pageIndex) {
		
		String json = Http.get(String.format("%s%s?page=%d",BASE_URL,movieApi.name().toLowerCase(),pageIndex), BodyHandlers.ofString());
		
		Movies movies = gson.fromJson(json, Movies.class);
				
		return movies;	
	}
	

	@Override
	public List<Node> getCardMovies(String json) {
		
		List<Node> list = new ArrayList<>();

		try {
			
			Gson gson = new Gson();

			Movies movies = gson.fromJson(json, Movies.class);

			List<Movie> listMovies = movies.getResults();

			for (Movie movie : listMovies) {
				Pane pane = navigationService.getNavigator(Url.CARD_MOVIES, movie).navigate();
				list.add(pane);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}


	




	

	
	
	
	
}
