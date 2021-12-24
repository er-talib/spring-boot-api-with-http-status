package com.devtols.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devtols.dao.BooksRepository;
import com.devtols.entities.Books;

@Component
public class BookService {

	@Autowired
	private BooksRepository booksRepository;

	// This list useg for store temprory data
//	private static List<Books> list = new ArrayList<>();
//	static {
//
//		list.add(new Books(123, "The power of Army", "About Army", "Armstrong"));
//		list.add(new Books(124, "Made world", "About World", "Talib"));
//		list.add(new Books(125, "The power of Air Force", "Air Force", "Aleem"));
//	}

	public List<Books> getAllBooks() {
//		return list
		List<Books> book = (List<Books>) this.booksRepository.findAll();
		return book;
	}

	public Books getBooksById(int id) {
		Books book = null;
		try {
//			return list.stream().filter(e -> e.getId() == id).findFirst().get();

			book = this.booksRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;

	}

	public Books addBooks(Books b) {
//		list.add(b);

		Books result = this.booksRepository.save(b);
//		return b;
		return result;

	}

	public void deleteData(int id) {
//		list.stream().filter(book -> book.getId() != id).collect(Collectors.toList());
		this.booksRepository.deleteById(id);
	}

	public void update(Books book, int bookId) {
//
//		list.stream().map(b -> {
//			if (b.getId() == bookId) {
////			  b.setId(book.getId()); in case you can change your id
//				b.setName(book.getName());
//				b.setTitle(book.getTitle());
//				b.setAuther(book.getAuther());
//			}
//			return b;
//		}).collect(Collectors.toList());
		book.setId(bookId);
		this.booksRepository.save(book);

	}

}
