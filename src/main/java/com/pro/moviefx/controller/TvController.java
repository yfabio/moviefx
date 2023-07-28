package com.pro.moviefx.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.pro.moviefx.api.Tv;
import com.pro.moviefx.fx.CallbackController;
import com.pro.moviefx.task.TaskBuilder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;


public class TvController extends BaseController implements CallbackController<Tv> {

	@FXML
	private Label genres;

	@FXML
	private StackPane movieBackdropImage;

	@FXML
	private Circle movieCircle;

	@FXML
	private Label movieDateLang;

	@FXML
	private ImageView movieImage;

	@FXML
	private Label movieName;

	@FXML
	private Arc moviePercent;

	@FXML
	private Label moviePercentText;

	@FXML
	private Label movieYear;

	@FXML
	private Label overview;

	@FXML
	private Label tagline;

	@Override
	public void publish(Tv value) {
			
		new Thread(new TaskBuilder<Background>()
				.call(() ->  {
					Image image = new Image("https://image.tmdb.org/t/p/w780/".concat(value.getBackdrop_path()));
					// new BackgroundSize(width, height, widthAsPercentage, heightAsPercentage, contain, cover)
					BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
					// new BackgroundImage(image, repeatX, repeatY, position, size)
					BackgroundImage backgroundImage = new BackgroundImage(image,
							                                 BackgroundRepeat.ROUND, 
							                                 BackgroundRepeat.NO_REPEAT, 
							                                 BackgroundPosition.CENTER,								                                 
							                                 backgroundSize);
					// new Background(images...)
					Background background = new Background(backgroundImage);
					return background;
				})
				.succeeded(movieBackdropImage::setBackground)
				.build())
				.start();
		
		
		new Thread(new TaskBuilder<Image>()
				.call(() -> new Image("https://image.tmdb.org/t/p/w780/".concat(value.getPoster_path())))
				.succeeded(movieImage::setImage)
				.build())
				.start();	
		
		LocalDate dateRelease = LocalDate.parse(value.getFirst_air_date());
		
		String genresCombined = Arrays.stream(value.getGenres()).map(p -> p.name)
				              .collect(Collectors.joining(", "));
		
				
				
		if(value!=null) {
			
					
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

			
			
			movieName.setText(value.getOriginal_title());
			movieYear.setText(String.valueOf(dateRelease.getYear()));
			movieDateLang.setText(dateRelease.toString().concat(" ").concat(value.getOriginal_language()));
			genres.setText(genresCombined);			
			tagline.setText(value.getTagline());
			overview.setText(value.getOverview());
			moviePercent.setLength(percentLength);
			moviePercentText.setText("%.01f".formatted(value.getVote_average()).replace(".","").concat("%"));
			
			
		}
		
				
	}


	
	

}
