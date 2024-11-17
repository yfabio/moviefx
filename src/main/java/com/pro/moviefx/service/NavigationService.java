package com.pro.moviefx.service;

import com.pro.moviefx.fx.Url;
import com.pro.moviefx.navigation.Navigator;

import javafx.stage.Stage;


public interface NavigationService {
	public <T> Navigator loadView(Stage stage,Url url, T obj); 
	public <T> Navigator loadView(Url url, T obj);
	public <T> Navigator loadView(Url url);
}
