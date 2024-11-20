package com.pro.moviefx.service.impl;

import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;
import com.pro.moviefx.api.MovieApi;
import com.pro.moviefx.api.Movies;
import com.pro.moviefx.http.Http;
import com.pro.moviefx.model.Movie;
import com.pro.moviefx.service.MovieService;

public class MovieServiceImpl implements MovieService {

	private static final String BASE_URL = "https://api.themoviedb.org/3/movie/"; 
	private static final String SEARCH_BASE_URL = "https://api.themoviedb.org/3/discover/movie"; 
			 		
	private Gson gson = new Gson();
			
	@Override
	public Movies getMovies(MovieApi movieApi) {
		
		String json = Http.get(String.format("%s%s",BASE_URL,movieApi.name().toLowerCase()), BodyHandlers.ofString());
				
		Movies movies = gson.fromJson(json, Movies.class);
		wasRequestSuccessfull(movies);
										
		movies.setMovieApi(movieApi);
		
		return movies;	
	}
	
	
	@Override
	public Movies getMovies(MovieApi movieApi, Integer pageIndex) {
		
		String json = Http.get(String.format("%s%s?page=%d",BASE_URL,movieApi.name().toLowerCase(),pageIndex), BodyHandlers.ofString());
		
		Movies movies = gson.fromJson(json, Movies.class);
		wasRequestSuccessfull(movies);
				
		return movies;	
	}

	@Override
	public Movie getMovieById(Long id) {
		String json = Http.get(String.format("%s%d",BASE_URL,id),BodyHandlers.ofString());
		Movie movie = gson.fromJson(json,Movie.class);
		wasRequestSuccessfull(movie);
		return movie;
	}


	@Override
	public Movies getMoviesSortedBy(String sortBy,Integer pageIndex) {
		String json = Http.get(String.format("%s?page=%d&sort_by=%s",SEARCH_BASE_URL,pageIndex,sortBy), BodyHandlers.ofString());	
		Movies movies = gson.fromJson(json, Movies.class);
		wasRequestSuccessfull(movies);
		return movies;
	}
	
}
