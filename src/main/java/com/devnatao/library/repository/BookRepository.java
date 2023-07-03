package com.devnatao.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devnatao.library.model.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel, UUID>{
}
