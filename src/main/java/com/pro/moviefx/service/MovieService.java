package com.pro.moviefx.service;

import com.pro.moviefx.api.MovieApi;
import com.pro.moviefx.api.Movies;
import com.pro.moviefx.model.Movie;

public interface MovieService {
	
	Movies getMovies(MovieApi movieApi);
	
	public Movies getMovies(MovieApi movieApi,Integer pageIndex);
	
	Movie getMovieById(Long id);
	
}
