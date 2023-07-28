package com.pro.moviefx.app;

import com.pro.moviefx.fx.FxLoader;
import com.pro.moviefx.fx.FxLoader.Url;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("TMDB");
		Pane root = FxLoader.load(Url.MAIN).navigate();	
		Scene scene = new Scene(root);		
		stage.centerOnScreen();
		stage.setScene(scene);
		stage.show();
		
	}

}
