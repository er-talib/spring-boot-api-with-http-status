package com.devtols.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devtols.entities.Books;
import com.devtols.service.BookService;

//@Controller
@RestController
public class TestDevTool {

//	@RequestMapping(value = "/test", method = RequestMethod.GET)
//	@ResponseBody
	@Autowired
	BookService bookService;

//	This Get request use without http status; 
//	@GetMapping("/books")
//	public List<Books> getBooks() {
//    	
//    	return this.bookService.getAllBooks();
//}

//	This Request use to without http status
	@GetMapping("/books")
	public ResponseEntity<List<Books>> getBooks() {

		List<Books> list = this.bookService.getAllBooks();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
		}

	}

//	This Get request of singel book use without http status;
//	@GetMapping("/books/{id}")
//	public Books getBook(@PathVariable("id") int id) {
//		return this.bookService.getBooksById(id);
//	}

	// This get request of singel book use with http status;
	@GetMapping("/books/{id}")
	public ResponseEntity<Books> getBook(@PathVariable("id") int id) {
		Books book = bookService.getBooksById(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.of(Optional.of(book));
		}
	}

////	This post request using without http statue
//	@PostMapping("/books")
//	public Books add(@RequestBody Books book) {
//		Books b = this.bookService.addBooks(book);
//		System.out.println(book);
//		return b;
//	}

	// This post request using with http statue
	@PostMapping("/books")
	public ResponseEntity<Books> add(@RequestBody Books book) {
		Books b = null;
		try {
			b = this.bookService.addBooks(book);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//	This delete request used to without http status 
//	@DeleteMapping("/books/{bookId}")
//	public void delete(@PathVariable("bookId") int bookId) {
//
//		this.bookService.deleteData(bookId);
//	}

//	This delete request used with http status
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Void> delete(@PathVariable("bookId") int bookId) {
		try {
			this.bookService.deleteData(bookId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

////	This request used without http status
//	@PutMapping("books/{bookId}")
//	public Books updateBooks(@RequestBody Books book, @PathVariable("bookId") int bookId) {
//
//		this.bookService.update(book, bookId);
//		return book;
//	}

//	This request used with http status
	@PutMapping("books/{bookId}")
	public ResponseEntity<Books> updateBooks(@RequestBody Books book, @PathVariable("bookId") int bookId) {
		try {
			this.bookService.update(book, bookId);
			return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
