package com.pro.moviefx.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.pro.moviefx.navigation.Navigator;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class BaseController implements Initializable  {

	protected String[] headers = {"Content-Type","application/json","Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzNjY4MWYxMDgwNTM3YTI1ZTZiNGY4NjEzMDY3ZTA5ZSIsInN1YiI6IjYwMGUxZmNjZDU1YzNkMDAzZDcwNDMwYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.L5NB1CY3MVUH1SPdovn3wxuyyrYIO3yE0r_korXFucw"};
	
	protected static final ObjectProperty<Navigator> navigation = new SimpleObjectProperty<Navigator>();

	protected static AtomicInteger nextInt = new AtomicInteger(1);
	
	protected static List<String> whichDoILoadMore = new ArrayList<>();
		
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
