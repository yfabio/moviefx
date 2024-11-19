package com.pro.moviefx.controller;

import java.net.URL;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pro.moviefx.navigation.Navigator;
import com.pro.moviefx.task.Job;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class BaseController implements Initializable {

	protected static final ObjectProperty<Navigator> navgiation = new SimpleObjectProperty<Navigator>();

	private static Set<Stage> set = new HashSet<>();
	
	protected NumberFormat nf = NumberFormat.getPercentInstance();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public Stage getStage() {
		return set.iterator().next();
	}

	public void setStage(Stage stage) {
		set.add(stage);
	}
	
	protected void alertError(Throwable error) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("");
		alert.setContentText(error.getMessage());
		alert.showAndWait();
	}
		
	protected <T> Job<T> task(Supplier<T> provider, Consumer<T> succeeded,Consumer<Throwable> error) {
		Job<T> job = new Job<T>(provider, succeeded,error);
		Thread worker = new Thread(job);
		worker.start();
		return job;
	}

	protected <T> Job<T> task(Supplier<T> provider, Consumer<T> succeeded) {
		Job<T> job = new Job<T>(provider, succeeded);
		Thread worker = new Thread(job);
		worker.start();
		return job;
	}
		
	protected <T> Job<T> task(Supplier<T> provider) {
		Job<T> job = new Job<T>(provider);
		Thread worker = new Thread(job);	
		worker.start();
		return job;
	}
	
	
	
}
