package com.sree.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrow_records")
public class BorrowRecord {
	@Id
	private String id;

	@JsonBackReference("book-ref")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id")
	private Book book;

	 @JsonBackReference(value = "borrower-ref")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "borrower_id")
	private Borrower borrower;


	private LocalDate borrowDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private Double fineAmount;


	@PrePersist
	public void prePersist() { if (id == null) id = UUID.randomUUID().toString(); }


	public BorrowRecord() {}
	public void setId(String id) {
		this.id = id;
	}
	
	public LocalDate getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	public Borrower getBorrower() {
		return borrower;
	}


	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}


	public Double getFineAmount() {
		return fineAmount;
	}


	public void setFineAmount(Double fineAmount) {
		this.fineAmount = fineAmount;
	}


	public String getId() {
		return id;
	}


	public BorrowRecord(String id, Book book, Borrower borrower, LocalDate borrowDate, LocalDate dueDate,
			LocalDate returnDate, Double fineAmount) {
		super();
		this.id = id;
		this.book = book;
		this.borrower = borrower;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.fineAmount = fineAmount;
	}


	@Override
	public String toString() {
		return "BorrowRecord [id=" + id + ", book=" + book + ", borrower=" + borrower + ", borrowDate=" + borrowDate
				+ ", dueDate=" + dueDate + ", returnDate=" + returnDate + ", fineAmount=" + fineAmount + "]";
	}
	
}