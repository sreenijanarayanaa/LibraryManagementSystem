package com.sree.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	
	
	@Id
	private String id;
	private String title;
	private String author;
	private String category;// (e.g. Fiction, Tech, History)  
	private boolean isAvailable = true;
	private int totalCopies;
	private int availableCopies;
	private boolean deleted = false;

	@JsonManagedReference("book-ref")
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private List<BorrowRecord> borrowRecords = new ArrayList<>();


	@PrePersist
	public void prePersist() { if (id == null) id = UUID.randomUUID().toString(); }
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public int getTotalCopies() {
		return totalCopies;
	}
	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}
	public int getAvailableCopies() {
		return availableCopies;
	}
	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", category=" + category
				+ ", isAvailable=" + isAvailable + ", totalCopies=" + totalCopies + ", availableCopies="
				+ availableCopies + ", deleted=" + deleted + ", borrowRecords=" + borrowRecords + "]";
	}
	public Book(String id, String title, String author, String category, boolean isAvailable, int totalCopies,
			int availableCopies) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.isAvailable = isAvailable;
		this.totalCopies = totalCopies;
		this.availableCopies = availableCopies;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Book(String id, String title, String author, String category, boolean isAvailable, int totalCopies,
			int availableCopies, boolean deleted, List<BorrowRecord> borrowRecords) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.isAvailable = isAvailable;
		this.totalCopies = totalCopies;
		this.availableCopies = availableCopies;
		this.deleted = deleted;
		this.borrowRecords = borrowRecords;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public boolean isDeleted() {
		return deleted;
	}


	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}


	public List<BorrowRecord> getBorrowRecords() {
		return borrowRecords;
	}


	public void setBorrowRecords(List<BorrowRecord> borrowRecords) {
		this.borrowRecords = borrowRecords;
	}


	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
	
}
