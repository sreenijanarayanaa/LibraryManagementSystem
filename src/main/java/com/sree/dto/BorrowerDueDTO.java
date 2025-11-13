package com.sree.dto;

import java.time.LocalDate;

public class BorrowerDueDTO {

	    private String borrowerName;
	    private LocalDate dueDate;
	    private String bookTitle;
		public String getBorrowerName() {
			return borrowerName;
		}
		public void setBorrowerName(String borrowerName) {
			this.borrowerName = borrowerName;
		}
		public LocalDate getDueDate() {
			return dueDate;
		}
		public void setDueDate(LocalDate dueDate) {
			this.dueDate = dueDate;
		}
		public String getBookTitle() {
			return bookTitle;
		}
		public void setBookTitle(String bookTitle) {
			this.bookTitle = bookTitle;
		}
		
		 public BorrowerDueDTO(String borrowerName, String bookTitle, LocalDate dueDate) {
		        this.borrowerName = borrowerName;
		        this.bookTitle = bookTitle;
		        this.dueDate = dueDate;
		    }
	   
	}