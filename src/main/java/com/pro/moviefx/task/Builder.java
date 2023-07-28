package com.pro.moviefx.task;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pro.moviefx.fx.Command;

import javafx.concurrent.Task;

public interface Builder<T> {

	Builder<T> succeeded(Consumer<T> succeed);

	Builder<T> scheduled(Command schedule);

	Builder<T> call(Supplier<T> work);
	
	Builder<T> error(Consumer<Throwable> error);
	
	Task<T> build();

}
