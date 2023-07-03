package com.devnatao.library.dto;

import java.util.UUID;

import org.springframework.hateoas.RepresentationModel;

public class BookMinimalDTO extends RepresentationModel<BookMinimalDTO> {
	
	private UUID bookId;
	private String title;
	private String genre;
	
	public UUID getBookId() {
		return bookId;
	}
	
	public void setBookId(UUID bookId) {
		
		this.bookId = bookId;
	}
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
}
