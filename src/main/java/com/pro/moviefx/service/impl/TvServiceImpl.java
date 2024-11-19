package com.pro.moviefx.service.impl;

import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;
import com.pro.moviefx.api.TvApi;
import com.pro.moviefx.api.Tvs;
import com.pro.moviefx.http.Http;
import com.pro.moviefx.model.Tv;
import com.pro.moviefx.service.TvService;

public class TvServiceImpl implements TvService {

	private static final String BASE_URL = "https://api.themoviedb.org/3/tv/";

	private Gson gson = new Gson();
	
	@Override
	public Tvs getTvs(TvApi tvApi) {
		
		String json = Http.get(String.format("%s%s", BASE_URL, tvApi.name().toLowerCase()), BodyHandlers.ofString());

		Tvs tvs = gson.fromJson(json, Tvs.class);		
		wasRequestSuccessfull(tvs);
		tvs.setTvApi(tvApi);

		return tvs;
	}

	@Override
	public Tvs getTvs(TvApi tvApi, Integer pageIndex) {
		
		String json = Http.get(String.format("%s%s?page=%d", BASE_URL, tvApi.name().toLowerCase(), pageIndex),
				BodyHandlers.ofString());

		Tvs tvs = gson.fromJson(json, Tvs.class);
		wasRequestSuccessfull(tvs);

		return tvs;
	}

	@Override
	public Tv getTvById(Long id) {
		String json = Http.get(String.format("%s%d",BASE_URL,id),BodyHandlers.ofString());
		Tv tv = gson.fromJson(json,Tv.class);
		wasRequestSuccessfull(tv);
		return tv;		
	}

}
