package com.pro.moviefx.task;

import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.concurrent.Task;

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
