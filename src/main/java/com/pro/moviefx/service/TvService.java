package com.pro.moviefx.service;

import com.pro.moviefx.api.TvApi;
import com.pro.moviefx.api.Tvs;

public interface TvService {

		
	Tvs getTvs(TvApi movieApi);
	
	Tvs getTvs(TvApi movieApi,Integer pageIndex); 
	
}
