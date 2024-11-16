package com.pro.moviefx.api;

import java.util.ArrayList;
import java.util.List;

import com.pro.moviefx.model.Tv;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tvs extends Tmdb {

	private List<Tv> results = new ArrayList<>();
	private TvApi tvApi;	
	
	
}
