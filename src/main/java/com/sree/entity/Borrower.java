package com.sree.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrowers")
public class Borrower {
	
	@Id
	private String id;
	private String name;
	private String email;


	@Enumerated(EnumType.STRING)
	private MembershipType membershipType;
	private int maxBorrowLimit;

	@JsonManagedReference("borrower-ref")
	@OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL)
	private List<BorrowRecord> borrowRecords = new ArrayList<>();


	@PrePersist
	public void prePersist() {
	if (id == null) id = UUID.randomUUID().toString();
	if (membershipType == null) membershipType = MembershipType.BASIC;
	if (maxBorrowLimit == 0) maxBorrowLimit = membershipType == MembershipType.PREMIUM ? 5 : 2;
	}


	public enum MembershipType { BASIC, PREMIUM }


	public Borrower() {}

	
	
	public String getId() {
		return id;
	}



	public void setId(String uuid) {
		this.id = uuid;
	}



	public List<BorrowRecord> getBorrowRecords() {
		return borrowRecords;
	}



	public void setBorrowRecords(List<BorrowRecord> borrowRecords) {
		this.borrowRecords = borrowRecords;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public MembershipType getMembershipType() {
		return membershipType;
	}
	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}
	public int getMaxBorrowLimit() {
		return maxBorrowLimit;
	}
	public void setMaxBorrowLimit(int maxBorrowLimit) {
		this.maxBorrowLimit = maxBorrowLimit;
	}



	public Borrower(String id, String name, String email, MembershipType membershipType, int maxBorrowLimit,
			List<BorrowRecord> borrowRecords) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.membershipType = membershipType;
		this.maxBorrowLimit = maxBorrowLimit;
		this.borrowRecords = borrowRecords;
	}



	@Override
	public String toString() {
		return "Borrower [id=" + id + ", name=" + name + ", email=" + email + ", membershipType=" + membershipType
				+ ", maxBorrowLimit=" + maxBorrowLimit + ", borrowRecords=" + borrowRecords + "]";
	}
	
}