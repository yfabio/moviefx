package com.pro.moviefx.controller;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pro.moviefx.navigation.Navigator;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class BaseController implements Initializable {

	protected static final ObjectProperty<Navigator> navigation = new SimpleObjectProperty<Navigator>();

	private static Set<Stage> set = new HashSet<>();

	public class Job<T> extends Task<T> {

		private Supplier<T> provider;
		private Consumer<T> succeeded;

		public Job(Supplier<T> provider, Consumer<T> succeeded) {
			this.provider = provider;			
			this.succeeded = succeeded;
		}

		public Job(Supplier<T> provider) {
			this.provider = provider;		
		}

		@Override
		protected T call() throws Exception {
			return provider.get();
		}
		
		@Override
		protected void succeeded() {
			if (succeeded != null) {
				succeeded.accept(getValue());
			}
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	public Stage getStage() {
		return set.iterator().next();
	}

	public void setStage(Stage stage) {
		set.add(stage);
	}

	protected <T> void action(Supplier<T> provider, Consumer<T> succeeded) {
		Job<T> task = new Job<T>(provider, succeeded);
		Thread worker = new Thread(task);
		worker.start();
	}

	protected <T> Callable<T> task(Supplier<T> provider) {
		Job<T> task = new Job<T>(provider);
		Thread worker = new Thread(task);
		Callable<T> result = () -> task.get();
		worker.start();
		return result;
	}

}
