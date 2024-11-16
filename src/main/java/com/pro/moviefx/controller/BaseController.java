package com.pro.moviefx.controller;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.pro.moviefx.navigation.Navigator;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class BaseController implements Initializable  {
	
	protected static final ObjectProperty<Navigator> navigation = new SimpleObjectProperty<Navigator>();

	protected static AtomicInteger nextInt = new AtomicInteger(1);
	
	protected static Set<String> whichDoILoadMore = new HashSet<>();
	
	private static Set<Stage> set = new HashSet<>();
		
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
	}

	public Stage getStage() {
		return set.iterator().next();
	}

	public void setStage(Stage stage) {
		set.add(stage);
	}

	
	
}
