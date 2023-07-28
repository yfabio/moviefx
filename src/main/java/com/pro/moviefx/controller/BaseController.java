package com.pro.moviefx.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import com.pro.moviefx.navigation.Navigator;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;

public class BaseController implements Initializable  {

	protected String[] headers = {"Content-Type","application/json","Authorization","Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1MTIzODRlOTE1ZThlZjU1ODdmNWQ3MWY0M2EwMTVlZiIsInN1YiI6IjYwMGUxZmNjZDU1YzNkMDAzZDcwNDMwYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Hf-RbtGDC_vUZ7OMHZrYyJKbFUi2bAZs4YquTdPjCUI"};
	
	protected static final ObjectProperty<Navigator> navigation = new SimpleObjectProperty<Navigator>();

	protected static AtomicInteger nextInt = new AtomicInteger(1);
	
	protected static List<String> whichDoILoadMore = new ArrayList<>();
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {		
	}

	
	
}
