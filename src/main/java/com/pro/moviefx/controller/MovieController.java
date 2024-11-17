package com.pro.moviefx.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.pro.moviefx.fx.CallbackController;
import com.pro.moviefx.model.Movie;
import com.pro.moviefx.resource.Resource;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;

public class MovieController extends BaseController implements CallbackController<Movie> {

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
	private Label runtime;

	@FXML
	private Label tagline;
	
	
	@Override
	public void accept(Movie value) {

		task(() -> {
			Image image = new Image("https://image.tmdb.org/t/p/w780/".concat(value.getBackdrop_path()));

			BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);

			BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.ROUND,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
			
			return new Background(backgroundImage);			
		}, movieBackdropImage::setBackground);

		task(() -> new Image("https://image.tmdb.org/t/p/w780/".concat(value.getPoster_path())),movieImage::setImage);
		

		LocalDate dateRelease = LocalDate.parse(value.getRelease_date());
		
		movieYear.setText(String.valueOf(dateRelease.getYear()));
		movieDateLang.setText(dateRelease.toString().concat(" ").concat(value.getOriginal_language()));

		String genresCombined = Arrays.stream(value.getGenres()).map(p -> p.name).collect(Collectors.joining(", "));
		genres.setText(genresCombined);
		
		Duration movieTimeLength = Duration.ofMinutes(value.getRuntime());

		if (value != null) {

			String percentageVote = nf.format(value.getVote_average() * 100 / 1000);
			
			double percentageVoteValue = Double.parseDouble(percentageVote.replace("%", ""));

			double percentLength = 360 * percentageVoteValue / 100;
									
			if(percentageVoteValue > 70) {				
				movieCircle.setStroke(Color.valueOf(Resource.getValue("circle.behind.green")));
				moviePercent.setStroke(Color.valueOf(Resource.getValue("circle.over.green")));
			}else if(percentageVoteValue < 70 && percentageVoteValue <= 50) {
				movieCircle.setStroke(Color.valueOf(Resource.getValue("circle.behind.yellow")));
				moviePercent.setStroke(Color.valueOf(Resource.getValue("circle.over.yellow")));
			}else {
				movieCircle.setStroke(Color.valueOf(Resource.getValue("circle.behind.red")));
				moviePercent.setStroke(Color.valueOf(Resource.getValue("circle.over.red")));
			}	
			
		
			movieName.setText(value.getOriginal_title());				
			runtime.setText("%dh %dm".formatted(movieTimeLength.toHoursPart(), movieTimeLength.toMinutesPart()));
			tagline.setText(value.getTagline());
			overview.setText(value.getOverview());
			moviePercent.setLength(percentLength);
			moviePercentText.setText(percentageVote);

		}

	}

}
