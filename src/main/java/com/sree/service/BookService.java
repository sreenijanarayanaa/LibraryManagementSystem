package com.sree.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sree.Exception.BookNotFoundException;
import com.sree.Exception.InvalidOperationException;
import com.sree.entity.Book;
import com.sree.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public Book addOrUpdateBook(Book book) {
		return bookRepository.save(book);

	}

	/*
	 * public List<Book> getBooks(String category, String available) { if (category
	 * != null && available != null) { Boolean isAvailable =
	 * Boolean.parseBoolean(available); return
	 * bookRepository.findByCategoryAndIsAvailable(category, isAvailable);
	 * 
	 * } else if (category != null) { return
	 * bookRepository.findByCategory(category); } else if (available != null) {
	 * Boolean isAvailable = Boolean.parseBoolean(available); return
	 * bookRepository.findByIsAvailable(isAvailable); } else { return
	 * bookRepository.findAll(); }
	 * 
	 * }
	 */

	
	public Page<Book> getBooks(String category, String available, int page, int size, String sortBy, String sortDir) {
	    Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	    Pageable pageable = PageRequest.of(page, size, sort);

	    if (category != null && available != null) {
	        boolean isAvailable = Boolean.parseBoolean(available);
	        return bookRepository.findByCategoryAndIsAvailable(category, isAvailable, pageable);
	    } else if (category != null) {
	        return bookRepository.findByCategory(category, pageable);
	    } else if (available != null) {
	        boolean isAvailable = Boolean.parseBoolean(available);
	        return bookRepository.findByIsAvailable(isAvailable, pageable);
	    } else {
	        return bookRepository.findAll(pageable);
	    }
	}

	public Book updateBook(String id,Book updateBook) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
        book.setAuthor(updateBook.getAuthor());
        book.setAvailableCopies(updateBook.getAvailableCopies());
        book.setCategory(updateBook.getCategory());
        book.setTitle(updateBook.getTitle());
        book.setTotalCopies(updateBook.getTotalCopies());
        book.setIsAvailable(updateBook.getIsAvailable());
        return bookRepository.save(book);
		//return book;

	}

	public void deleteBook(int id) {
		// TODO Auto-generated method stub

	}

	public Book softDeleteBook(String id) {
		Book book = bookRepository.findById(id).orElseThrow(()->new BookNotFoundException("Book with id: "+id+" doesn't exsist"));
		if(book.getAvailableCopies()!=book.getTotalCopies()) {
			throw new InvalidOperationException("Cannot delete book with active borrow records");
		}
        book.setIsAvailable(false);
       return  bookRepository.saveAndFlush(book);
        //return bookRepository.delete(book);
		
	}

	
}
