package com.pro.moviefx.fx;

import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.google.gson.Gson;
import com.pro.moviefx.api.Media;
import com.pro.moviefx.api.Movie;
import com.pro.moviefx.api.Movies;
import com.pro.moviefx.api.Tv;
import com.pro.moviefx.api.Tvs;
import com.pro.moviefx.http.Http;
import com.pro.moviefx.task.TaskBuilder;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.stage.Window;

public class ContextMenuBuilder  {

	private ContextMenu contextMenu = new ContextMenu();
	private Control node;
	private Double x;
	private Double y;
	
	public ContextMenuBuilder(Control node) {
		this.node = node;
		
		Scene scene = node.getScene();
		Window window = node.getScene().getWindow();			
		Point2D coordination = node.localToScene(0.0, 0.0);			
		x = coordination.getX();
		y = coordination.getY();			
		x += scene.getX() + window.getX();
		y += scene.getY() + window.getY() + 28;			
			
		contextMenu.setAutoHide(true);	
		contextMenu.setAutoFix(true);
		contextMenu.setConsumeAutoHidingEvents(true);
		contextMenu.setHideOnEscape(true);
			
	}	
		
	public <T> ContextMenuBuilder addMenuItem(String title,String url,String[] headers,Class<T> clazz, Consumer<List<? extends Media>> action) {
		MenuItem menuItem = new MenuItem(title);	
		contextMenu.getItems().add(menuItem);
		menuItem.setOnAction(evt -> {
			new Thread(new TaskBuilder<List<? extends Media>>().call(() -> {	
				
				List<Media> list = new ArrayList<>();
				
				if(url.equals("")) {
					return list;
				}					
				
				String json = new StringBuilder(Http.get(url, BodyHandlers.ofString(), headers)).toString();
				
				Gson gson = new Gson();	
				
				if(clazz.equals(Movie.class)) {
					Movies movies = gson.fromJson(json, Movies.class);
					list.addAll(movies.getResults());
				}else if(clazz.equals(Tv.class)) {
					Tvs tvs = gson.fromJson(json, Tvs.class);
					list.addAll(tvs.getResults());
				}else if(clazz.equals(null)) {
					
				}			
				return list;
			}).succeeded(items -> {
				action.accept(items);
			}).build()).start();
		});
		
		return this;
	}
	
	
	public ContextMenuBuilder closeContextMenu(Consumer<ContextMenu> action) {
		action.accept(contextMenu);
		return this;
	}
	

	public void show() {			
		if(!contextMenu.isShowing()) {
			node.setContextMenu(contextMenu);	
			contextMenu.show(node,x,y);
		}
	}
	
}
