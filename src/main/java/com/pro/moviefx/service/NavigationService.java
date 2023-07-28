package com.pro.moviefx.service;

import java.util.function.Supplier;

import com.pro.moviefx.controller.BaseController;
import com.pro.moviefx.navigation.Navigator;


public interface NavigationService {
	public <T> Navigator getNavigator(Class<? extends BaseController> controller,Supplier<T> obj);
	public <T> Navigator getNavigator(Class<? extends BaseController> controller);
}
