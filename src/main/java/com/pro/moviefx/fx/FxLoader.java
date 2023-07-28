package com.pro.moviefx.fx;

import java.io.IOException;
import java.util.function.Supplier;

import com.pro.moviefx.navigation.Navigator;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FxLoader {

	public enum Url {

		MAIN("/fxml/main.fxml"), 
		HOME("/fxml/home.fxml"),
		CARD_MOVIES("/fxml/card-movies.fxml"),
		CARD_TVS("/fxml/card-tvs.fxml"),
		MOVIE("/fxml/movie.fxml"), 
		TV("/fxml/tv.fxml");
		
		
		private String path;

		private Url(String path) {
			this.path = path;
		}

		public String getPath() {
			return path;
		}

	}
		
	public static <T> Navigator load(Url url,Supplier<T> obj)  {
		
		FXMLLoader loader = new FXMLLoader(FxLoader.class.getResource(url.getPath()));
				
		Navigator navigator = new Navigator() {		
			
			@Override
			public Pane navigate() {				
				Pane pane = new Pane();	
				try {	
					pane = loader.load();					
					if(obj!=null) {
						CallbackController<T> callback = loader.getController();
						callback.publish(obj.get());
					}
					
				} catch (IOException e) {					
				}
				return pane;
			}
		};
				
		return navigator;
	}
	
	public static <T> Navigator load(Url url)  {
		return load(url, null);
	}
	
	
	
	
}
