package com.devnatao.library.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devnatao.library.controller.BookController;
import com.devnatao.library.dto.BookModelDTO;
import com.devnatao.library.mapper.DozerMapper;
import com.devnatao.library.model.BookModel;
import com.devnatao.library.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	
	@Transactional
	public BookModel create(BookModelDTO data) {
		var entity = DozerMapper.parseObject(data, BookModel.class);
		
		UUID uuid = UUID.randomUUID();
		entity.setBookId(uuid);
		
		entity.add(
				linkTo
				(methodOn(
						BookController.class).findBookById(
								entity.getBookId())).withSelfRel());
		
		return repository.save(entity);
	}
	
	@Transactional
	public void delete(UUID id) {
		repository.deleteById(id);
	}
	
	@Transactional
	public BookModel update(UUID id, BookModelDTO data) {
		Optional<BookModel> optionalEntity = repository.findById(id);
		var entity = optionalEntity.get();

		entity.setBookId(optionalEntity.get().getBookId());
		entity.setTitle(data.getTitle());
		entity.setGenre(data.getGenre());
		entity.setAuthor(data.getAuthor());
		entity.setEditorial(data.getEditorial());
		entity.setPublishedYear(data.getPublishedYear());
		
		entity.add(
				linkTo(
						methodOn(
								BookController.class).findBookById(
										entity.getBookId())).withSelfRel());
		
		return entity;
	}
	
	@Transactional(readOnly = true)
	public BookModel findById(UUID id) {
		Optional<BookModel> optionalEntity = repository.findById(id);
		var entity = optionalEntity.get();
		
		entity.add(
				linkTo(
						methodOn(
								BookController.class)
						.findAllRegisteredBooks()).withRel("Voltar ao catálogo de livros"));
		
		return entity;
	}
	
	@Transactional(readOnly = true)
	public List<BookModel> findAll() {
		List<BookModel> responseList = repository.findAll();
		
		responseList.stream().map(o -> o.add(
				linkTo(
						methodOn(
								BookController.class)
						.findBookById(o.getBookId()))
				.withSelfRel()))
		.collect(Collectors.toList());
		
		return responseList;
	}
}
