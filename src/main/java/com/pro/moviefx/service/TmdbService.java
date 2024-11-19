package com.pro.moviefx.service;

import com.pro.moviefx.api.Tmdb;
import com.pro.moviefx.exception.ResourceNotFoundException;

public interface TmdbService {
	
	public default void wasRequestSuccessfull(Tmdb tmdb) {
		if(tmdb.getSuccess()!=null && !tmdb.getSuccess()) {
			throw new ResourceNotFoundException(tmdb.getStatus_message());	
		}	
	}
}
