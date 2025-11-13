package com.sree.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sree.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,String>{

	List<Book> findByCategoryAndIsAvailable(String category, boolean available);

	List<Book> findByIsAvailable(boolean available);

	List<Book> findByCategory(String category);
	
	
	    Page<Book> findByCategory(String category, Pageable pageable);
	    Page<Book> findByIsAvailable(boolean isAvailable, Pageable pageable);
	    Page<Book> findByCategoryAndIsAvailable(String category, boolean isAvailable, Pageable pageable);
	


	
}
