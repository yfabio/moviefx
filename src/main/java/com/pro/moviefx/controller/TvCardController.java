package com.pro.moviefx.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.pro.moviefx.fx.CallbackController;
import com.pro.moviefx.fx.Url;
import com.pro.moviefx.http.Http;
import com.pro.moviefx.model.Tv;
import com.pro.moviefx.service.NavigationService;
import com.pro.moviefx.service.impl.NavigationServiceImpl;
import com.pro.moviefx.task.TaskBuilder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;



public class TvCardController extends BaseController  implements CallbackController<Tv> {

	@FXML
	private ImageView cardImage;
	
	@FXML
	private Circle movieCircle;

	@FXML
	private Label cardDate;

	@FXML
	private Label cardName;

	@FXML
	private Arc moviePercent;

	@FXML
	private Label cardPercentText;

	@FXML 
	private VBox card;
	
	
	private NavigationService navigationService = new NavigationServiceImpl();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
	}

	@Override
	public void accept(Tv value) {	
		try {
								
			new Thread(new TaskBuilder<Image>()	
					.scheduled(() -> {
						card.setOnMouseClicked(evt -> onMovieSelected(value.getId()));						
					})
					.call(() -> new Image("https://image.tmdb.org/t/p/w300/".concat(value.getPoster_path())))
					.succeeded(image -> {
						Image img = image;
						cardImage.setImage(img);						
					})
					.build()).start();	
			
			
			movieCircle.getStyleClass().clear();
			moviePercent.getStyleClass().clear();
			
			double result = BigDecimal.valueOf(value.getVote_average())
									  .multiply(BigDecimal.valueOf(100))
									  .doubleValue();
			
			double percentLength = BigDecimal.valueOf(value.getVote_average())
											 .multiply(BigDecimal.valueOf(100))
											 .divide(BigDecimal.valueOf(1000))
											 .multiply(BigDecimal.valueOf(360))
											 .doubleValue();
									  
			movieCircle.getStyleClass().add("progress-circle");
			moviePercent.getStyleClass().add("progress-percent");
			
			if(result >= 700) {				
				movieCircle.getStyleClass().add("progress-circle-green-5");
				moviePercent.getStyleClass().add("progress-percent-green");
			}else if(result >= 400  && result <= 690) {
				movieCircle.getStyleClass().add("progress-circle-yellow-5");
				moviePercent.getStyleClass().add("progress-percent-yellow");
			}else {
				movieCircle.getStyleClass().add("progress-circle-red-5");
				moviePercent.getStyleClass().add("progress-percent-red");
			}
			
			moviePercent.setLength(percentLength);
			cardName.setText(value.getOriginal_name());
			cardDate.setText(value.getFirst_air_date());
						
			cardPercentText.setText(String.valueOf(value.getVote_average()).replace(".", "").concat("%"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}

	private void onMovieSelected(Long id) {		
		
		new Thread(new TaskBuilder<Tv>()
				.call(() -> {					
					String json =Http.get("https://api.themoviedb.org/3/tv/%d".formatted(id), BodyHandlers.ofString());					
					Gson gson = new Gson();					
					return gson.fromJson(json, Tv.class);					
				})
				.succeeded(tv -> {									
					navigation.set(navigationService.getNavigator(Url.TV,tv));					
				}).build()).start();
			
	}
	

}
