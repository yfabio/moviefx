package com.pro.moviefx.fx;

import com.pro.moviefx.navigation.Navigator;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class PlaceHolder extends StackPane {
	
	private ObjectProperty<Navigator> navigation = new SimpleObjectProperty<Navigator>();
	
	public PlaceHolder() {		
		navigation.addListener((evt,older,newer) -> {						
			this.getChildren().clear();
			this.getChildren().add(newer.navigate());			
			animate();
		});				
	}
	
	private void animate() {		
		Platform.runLater(() -> {			
			FadeTransition fade = new FadeTransition();
			fade.setFromValue(0.33);		
			fade.setToValue(1);
			fade.setDuration(Duration.seconds(0.33));		
			fade.setNode(this);	
			fade.play();			
		});			
	}
	
	
	
	public ObjectProperty<Navigator> getNavigation() {
		return navigation;
	}
	
	public void setNavigation(Navigator navigator) {
		this.navigation.set(navigator);
	}
}
