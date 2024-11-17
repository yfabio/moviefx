package com.pro.moviefx.controller;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pro.moviefx.navigation.Navigator;
import com.pro.moviefx.task.Job;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class BaseController implements Initializable {

	protected static final ObjectProperty<Navigator> navgiation = new SimpleObjectProperty<Navigator>();

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

	protected <T> Job<T> task(Supplier<T> provider, Consumer<T> succeeded) {
		Job<T> job = new Job<T>(provider, succeeded);
		Thread worker = new Thread(job);
		worker.start();
		return job;
	}

	protected <T> Callable<T> task(Supplier<T> provider) {
		Job<T> job = new Job<T>(provider);
		Thread worker = new Thread(job);
		Callable<T> result = () -> job.get();
		worker.start();
		return result;
	}

}
