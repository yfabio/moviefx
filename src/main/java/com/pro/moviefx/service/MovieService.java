package com.pro.moviefx.service;

import java.util.List;

import com.pro.moviefx.api.MovieApi;
import com.pro.moviefx.api.Movies;
import com.pro.moviefx.model.Media;

import javafx.scene.Node;

public interface MovieService {

	List<? extends Media> getMoviesList(MovieApi movieApi);
	
	Movies getMovies(MovieApi movieApi);
	
	public Movies getMovies(MovieApi movieApi,Integer pageIndex);
	
	List<Node> getCardMovies(String json);
	
}
