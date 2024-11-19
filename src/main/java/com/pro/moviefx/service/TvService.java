package com.pro.moviefx.service;

import com.pro.moviefx.api.TvApi;
import com.pro.moviefx.api.Tvs;
import com.pro.moviefx.model.Tv;

public interface TvService extends TmdbService {
		
	Tvs getTvs(TvApi movieApi);
	
	Tvs getTvs(TvApi movieApi,Integer pageIndex); 
	
	Tv getTvById(Long id);
	
}
