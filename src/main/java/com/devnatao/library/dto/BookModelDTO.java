package com.devnatao.library.dto;


public class BookModelDTO {

	private String title;
	private String genre;
	private String author;
	private String editorial;
	private String publishedYear;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getEditorial() {
		return editorial;
	}
	
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	public String getPublishedYear() {
		return publishedYear;
	}
	
	public void setPublishedYear(String publishedYear) {
		this.publishedYear = publishedYear;
	}
}
