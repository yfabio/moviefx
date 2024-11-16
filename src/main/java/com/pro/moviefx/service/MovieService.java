package com.pro.moviefx.service;

import java.util.List;

import com.pro.moviefx.api.Media;
import com.pro.moviefx.api.MovieApi;

import javafx.scene.Node;

public interface MovieService {

	List<? extends Media> getMovies(MovieApi movieApi);
	
	List<Node> getCardMovies(String json);
	
}
