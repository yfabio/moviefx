package com.pro.moviefx.fx;

public enum Url {
	
	MAIN("/fxml/main.fxml"),
	HOME("/fxml/home.fxml"),
	CARD_MOVIE("/fxml/card-movie.fxml"),
	CARD_TV("/fxml/card-tv.fxml"),
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
