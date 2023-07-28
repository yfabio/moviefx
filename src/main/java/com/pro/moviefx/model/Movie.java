package com.pro.moviefx.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Movie  {

	private ObjectProperty<Image> cardImage = new SimpleObjectProperty<>();
	private StringProperty name = new SimpleStringProperty();
	private StringProperty releaseDate = new SimpleStringProperty();
	private StringProperty percentage = new SimpleStringProperty();
		
	public ObjectProperty<Image> cardImageProperty() {		
		return this.cardImage;
	}
	
	public Image getCardImage() {
		return this.cardImageProperty().get();
	}
	
	public void setCardImage(final Image cardImage) {
		this.cardImageProperty().set(cardImage);
	}
	
	public StringProperty nameProperty() {
		return this.name;
	}
	
	public String getName() {
		return this.nameProperty().get();
	}
	
	public void setName(final String name) {
		this.nameProperty().set(name);
	}
	
	public StringProperty releaseDateProperty() {
		return this.releaseDate;
	}
	
	public String getReleaseDate() {
		return this.releaseDateProperty().get();
	}
	
	public void setReleaseDate(final String releaseDate) {
		this.releaseDateProperty().set(releaseDate);
	}
	
	public StringProperty percentageProperty() {
		return this.percentage;
	}
	
	public String getPercentage() {
		return this.percentageProperty().get();
	}
	
	public void setPercentage(final String percentage) {
		this.percentageProperty().set(percentage);
	}
	
	
	
	
}
