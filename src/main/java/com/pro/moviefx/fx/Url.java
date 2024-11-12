package com.pro.moviefx.fx;

public enum Url {
	
	MAIN("/fxml/main.fxml"),
	HOME("/fxml/home.fxml"),
	CARD_MOVIES("/fxml/cardMovies.fxml"),
	CARD_TVS("/fxml/cardTvs.fxml"),
	MOVIE("/fxml/movie.fxml"),
	TV("/fxml/tv.fxml");

	private String path;

	private Url(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
