package com.pro.moviefx.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.pro.moviefx.navigation.Navigator;
import com.pro.moviefx.resource.Resource;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class BaseController implements Initializable  {
	
	protected static final ObjectProperty<Navigator> navigation = new SimpleObjectProperty<Navigator>();

	protected static AtomicInteger nextInt = new AtomicInteger(1);
	
	protected static List<String> whichDoILoadMore = new ArrayList<>();
	
	private static Set<Stage> set = new HashSet<>();
	
	private static String tmdbKey;
	
	static {
		tmdbKey = Resource.getValue("tmdb.key");
	}
	
	protected String[] headers = {"Content-Type","application/json","Authorization","Bearer %s".formatted(tmdbKey)};
		
	
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
