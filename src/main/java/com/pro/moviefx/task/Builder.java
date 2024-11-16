package com.pro.moviefx.task;

import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.concurrent.Task;

public interface Builder<T> {

	Builder<T> succeeded(Consumer<T> succeed);

	Builder<T> scheduled(Runnable schedule);

	Builder<T> call(Supplier<T> work);
	
	Builder<T> error(Consumer<Throwable> error);
	
	Task<T> build();

}
