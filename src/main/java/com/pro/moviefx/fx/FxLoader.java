package com.pro.moviefx.fx;

import java.io.IOException;

import com.pro.moviefx.controller.BaseController;
import com.pro.moviefx.navigation.Navigator;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FxLoader {

			
	@SuppressWarnings("unchecked")
	public <T> Navigator load(Url url, T obj)  {
		
		FXMLLoader fxmlLoader = new FXMLLoader(FxLoader.class.getResource(url.getPath()));
				
		Navigator navigator = new Navigator() {		
			
			
			@Override
			public Pane navigate() {				
				Pane pane = new Pane();	
				try {	
					pane = fxmlLoader.load();
					BaseController base = fxmlLoader.getController();
					if(base instanceof CallbackController callback && obj != null) {						
						callback.accept(obj);
					}
					
				} catch (IOException e) {					
				}
				return pane;
			}
		};
				
		return navigator;
	}
	
	@SuppressWarnings("unchecked")
	public <T> Navigator load(Stage stage, Url url, T obj) {
		
		FXMLLoader fxmlLoader = new FXMLLoader(FxLoader.class.getResource(url.getPath()));

		Navigator navigator = new Navigator() {
			
			@Override
			public Pane navigate() {
				Pane pane = new Pane();
				try {
					pane = fxmlLoader.load();
					BaseController base = fxmlLoader.getController();
					if(stage!=null && base!= null) {
						base.setStage(stage);						
					}
					if (base instanceof CallbackController callback && obj != null) {
						callback.accept(obj);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
				return pane;
			}

		};
		
		return navigator;
	}
	
	
	
	
}
