package com.pro.moviefx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pro.moviefx.fx.CallbackController;
import com.pro.moviefx.fx.Url;
import com.pro.moviefx.model.Movie;
import com.pro.moviefx.resource.Resource;
import com.pro.moviefx.service.MovieService;
import com.pro.moviefx.service.NavigationService;
import com.pro.moviefx.service.impl.MovieServiceImpl;
import com.pro.moviefx.service.impl.NavigationServiceImpl;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;

public class CardMovie extends BaseController implements CallbackController<Movie> {

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
	
	private MovieService movieService = new MovieServiceImpl();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@Override
	public void accept(Movie value) {
		try {

			card.setOnMouseClicked(evt -> onMovieSelected(value.getId()));
			
			task(() -> new Image("https://image.tmdb.org/t/p/w300/".concat(value.getPoster_path())),
					cardImage::setImage);
			
			String percentageVote = "";
			
			if(value.getVote_average() != null) {
				percentageVote = nf.format(value.getVote_average() * 100 / 1000);				
			}else {
				percentageVote = nf.format(0);
			}
								
			double percentageVoteValue = Double.parseDouble(percentageVote.replace("%", ""));

			double percentLength = 360 * percentageVoteValue / 100;
			
			movieCircle.setStroke(Color.TRANSPARENT);
			moviePercent.setStroke(Color.TRANSPARENT);
									
			if(percentageVoteValue >= 70) {				
				movieCircle.setStroke(Color.valueOf(Resource.getValue("circle.behind.green")));
				moviePercent.setStroke(Color.valueOf(Resource.getValue("circle.over.green")));
			}else if(percentageVoteValue > 45 && percentageVoteValue <= 69) {
				movieCircle.setStroke(Color.valueOf(Resource.getValue("circle.behind.yellow")));
				moviePercent.setStroke(Color.valueOf(Resource.getValue("circle.over.yellow")));
			}else {
				movieCircle.setStroke(Color.valueOf(Resource.getValue("circle.behind.red")));
				moviePercent.setStroke(Color.valueOf(Resource.getValue("circle.over.red")));
			}
			
			
			moviePercent.setLength(percentLength);
			cardPercentText.setText(percentageVote);
			cardName.setText(value.getOriginal_title());
			cardDate.setText(value.getRelease_date());
					

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

	private void onMovieSelected(Long id) {
		task(() -> movieService.getMovieById(id),movie -> {
			navgiation.set(navigationService.loadView(Url.MOVIE, movie));
		});
	}

}
