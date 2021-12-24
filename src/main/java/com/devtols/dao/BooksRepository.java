package com.devtols.dao;

import org.springframework.data.repository.CrudRepository;

import com.devtols.entities.Books;

public interface BooksRepository extends CrudRepository<Books, Integer> {
	
	public Books findById(int id);

}
