package com.pro.moviefx.service.impl;

import java.util.function.Supplier;

import com.pro.moviefx.controller.BaseController;
import com.pro.moviefx.controller.HomeController;
import com.pro.moviefx.controller.MainController;
import com.pro.moviefx.controller.MovieController;
import com.pro.moviefx.controller.TvController;
import com.pro.moviefx.fx.FxLoader;
import com.pro.moviefx.fx.FxLoader.Url;
import com.pro.moviefx.navigation.Navigator;
import com.pro.moviefx.service.NavigationService;


public class NavigationServiceImpl implements NavigationService {

	@Override
	public <T> Navigator getNavigator(Class<? extends BaseController> controller,Supplier<T> obj) {
		
		if(controller.equals(MainController.class)) {		
			return FxLoader.load(Url.MAIN);			
		}else if(controller.equals(HomeController.class)) {
			return FxLoader.load(Url.HOME,obj);
		}else if(controller.equals(MovieController.class)) {
			return FxLoader.load(Url.MOVIE,obj);
		}else if(controller.equals(TvController.class)) {
			return FxLoader.load(Url.TV,obj);
		}
				
		return null;
	}

	@Override
	public <T> Navigator getNavigator(Class<? extends BaseController> controller) {
		return getNavigator(controller, null);
	}
}
