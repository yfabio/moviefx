package com.pro.moviefx.app;

import com.pro.moviefx.fx.Url;
import com.pro.moviefx.service.NavigationService;
import com.pro.moviefx.service.impl.NavigationServiceImpl;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

	private NavigationService navigate = new NavigationServiceImpl();
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("TMDB");
		Pane root = navigate.loadView(stage, Url.MAIN, null).navigate();	
		Scene scene = new Scene(root);		
		stage.centerOnScreen();
		stage.setScene(scene);
		stage.show();
		
	}

}
