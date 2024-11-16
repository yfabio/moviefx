package com.pro.moviefx.task;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;

public class Timeout {

	public static void setTimeout(Runnable cmd, long delay) {
		ScheduledExecutorService ex = Executors.newSingleThreadScheduledExecutor();
		try {
			ex.schedule(() -> {
				Platform.runLater(cmd);
			}, delay, TimeUnit.MILLISECONDS);
		} finally {
			ex.shutdown();
		}
	}

}
