package com.pro.moviefx.service.impl;

import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.google.gson.Gson;
import com.pro.moviefx.api.TvApi;
import com.pro.moviefx.api.Tvs;
import com.pro.moviefx.http.Http;
import com.pro.moviefx.model.Media;
import com.pro.moviefx.service.TvService;

public class TvServiceImpl implements TvService {

	private static final String BASE_URL = "https://api.themoviedb.org/3/tv/";

	private Gson gson = new Gson();

	
	@Override
	public Tvs getTvs(TvApi movieApi) {
		String json = Http.get(String.format("%s%s", BASE_URL, movieApi.name().toLowerCase()), BodyHandlers.ofString());

		Tvs tvs = gson.fromJson(json, Tvs.class);

		return tvs;
	}

	@Override
	public Tvs getTvs(TvApi tvApi, Integer pageIndex) {
		
		String json = Http.get(String.format("%s%s?page=%d", BASE_URL, tvApi.name().toLowerCase(), pageIndex),
				BodyHandlers.ofString());

		Tvs tvs = gson.fromJson(json, Tvs.class);

		return tvs;
	}

}
