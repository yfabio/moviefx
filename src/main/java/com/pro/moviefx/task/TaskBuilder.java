package com.pro.moviefx.task;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pro.moviefx.fx.Command;

import javafx.concurrent.Task;

public class TaskBuilder<T> implements Builder<T> {

	private Consumer<T> succeed;
	private Supplier<T> work;
	private Command schedule;
	private Consumer<Throwable> error;

	@Override
	public Builder<T> succeeded(Consumer<T> succeed) {
		this.succeed = succeed;
		return this;
	}

	@Override
	public Builder<T> scheduled(Command schedule) {
		this.schedule = schedule;
		return this;
	}

	@Override
	public Builder<T> call(Supplier<T> work) {
		this.work = work;
		return this;
	}
	
	@Override
	public Builder<T> error(Consumer<Throwable> error) {
		this.error = error;
		return this;
	}
	

	@Override
	public Task<T> build() {
		Task<T> task = new Task<T>() {
			@Override
			protected T call() throws Exception {
				return work.get();
			}
			@Override
			protected void scheduled() {
				if(schedule!=null) {
					schedule.execute();
				}				
			}
			@Override
			protected void succeeded() {
				succeed.accept(getValue());
			}
			
			@Override
			protected void setException(Throwable ex) {
				if(error != null) {
				  error.accept(ex);
				}
			}
			
			
			
		};
		return task;
	}

	

}
