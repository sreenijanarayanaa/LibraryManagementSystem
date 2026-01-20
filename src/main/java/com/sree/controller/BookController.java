package com.sree.controller;

import java.util.List;
import java.util.UUID;

//import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sree.entity.Book;
import com.sree.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {


	@Autowired
	BookService bookService;

	// POST /books – Add a new book or increase total copies if title already
	// exists.
	@PostMapping("/")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		return ResponseEntity.ok(bookService.addBook(book));
	}

	/*
	 * GET /books – List books with optional filters: ○ ?category=Tech ○
	 * ?available=true ○ Support pagination & sorting by title or author.
	 */
	/*
	 * @GetMapping("/") public ResponseEntity<List<Book>>
	 * getBooks(@RequestParam(required = false) String category,
	 * 
	 * @RequestParam(required = false) String available) { return
	 * ResponseEntity.ok(bookService.getBooks(category, available));
	 * 
	 * }
	 */
	
	@GetMapping("/")
	public ResponseEntity<Page<Book>> getBooks(
	        @RequestParam(required = false) String category,
	        @RequestParam(required = false) String available,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        @RequestParam(defaultValue = "title") String sortBy,
	        @RequestParam(defaultValue = "asc") String sortDir) {

	    Page<Book> books = bookService.getBooks(category, available, page, size, sortBy, sortDir);
	    return ResponseEntity.ok(books);
	}


	// PUT /books/{id} – Update metadata or adjust available copies.
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBooks(@PathVariable String id, @RequestBody Book book) {
		return ResponseEntity.ok(bookService.updateBook(id, book));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Book> deleteBook(@PathVariable String id) {

		Book deletedBook = bookService.DeleteBook(id);

		return ResponseEntity.ok(deletedBook);
	}

//line7567
	//line2
	
}
