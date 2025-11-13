package com.sree.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class FinePolicy {
	
	@Id
	private String id;
	private String category; 
	private final double finePerDay=10;
	public String getId() {
		return id;
	}
	public FinePolicy() {
       
    }

	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getFinePerDay() {
		return finePerDay;
	}
	
	@PrePersist
	public void prePersist() { if (id == null) id = UUID.randomUUID().toString(); }
	
	public FinePolicy(String id, String category) {
		super();
		this.id = id;
		this.category = category;
		
	}
	@Override
	public String toString() {
		return "FinePolicy [id=" + id + ", category=" + category + ", finePerDay=" + finePerDay + "]";
	} 
	
	

}
