package com.pro.moviefx.service;

import java.util.List;

import com.pro.moviefx.api.Media;
import com.pro.moviefx.api.TvApi;

public interface TvService {

	List<? extends Media> getMovies(TvApi movieApi);
	
	
}
