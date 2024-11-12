package com.pro.moviefx.service.impl;

import com.pro.moviefx.fx.FxLoader;
import com.pro.moviefx.fx.Url;
import com.pro.moviefx.navigation.Navigator;
import com.pro.moviefx.service.NavigationService;

import javafx.stage.Stage;


public class NavigationServiceImpl implements NavigationService {

	private FxLoader fxLoader = new FxLoader();

	@Override
	public <T> Navigator getNavigator(Stage stage, Url url, T obj) {
		return fxLoader.load(stage,url,obj);
	}

	@Override
	public <T> Navigator getNavigator(Url url, T obj) {
		return fxLoader.load(url,obj);
	}

	@Override
	public <T> Navigator getNavigator(Url url) {
		return fxLoader.load(url,null);
	}
	
	
	
}
