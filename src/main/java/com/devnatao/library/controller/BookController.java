package com.devnatao.library.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devnatao.library.dto.BookModelDTO;
import com.devnatao.library.model.BookModel;
import com.devnatao.library.service.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {
	
	@Autowired
	private BookService service;

	@PostMapping("/register")
	public ResponseEntity<BookModel> registerANewBook(@RequestBody BookModelDTO data) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(data));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOneBookById(@PathVariable UUID id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<BookModel> updateBookById(@RequestBody BookModelDTO data, @PathVariable UUID id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, data));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookModel> findBookById(@PathVariable UUID id) { 
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<BookModel>> findAllRegisteredBooks() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
}
