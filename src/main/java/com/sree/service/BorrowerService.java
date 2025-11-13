package com.sree.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sree.Exception.BorrowerNotFoundException;
import com.sree.entity.BorrowRecord;
import com.sree.entity.Borrower;
import com.sree.entity.Borrower.MembershipType;
import com.sree.repository.BorrowRecordRepository;
import com.sree.repository.BorrowRepository;


@Service
public class BorrowerService {
	
	@Autowired
	BorrowRepository borrowerRepository;
	@Autowired
	BorrowRecordRepository borrowRecordRepository;

	/**
     * Register a new borrower with membership rules.
     */
    public Borrower registerBorrower(Borrower borrower) {
        //borrower.setId(UUID.randomUUID());
        // Assign borrow limit based on membership type
        if (borrower.getMembershipType() == null) {
            borrower.setMembershipType(MembershipType.BASIC);
        }

        if (borrower.getMembershipType() == MembershipType.BASIC) {
            borrower.setMaxBorrowLimit(2);
        } else if (borrower.getMembershipType() == MembershipType.PREMIUM) {
            borrower.setMaxBorrowLimit(5);
        }

        return borrowerRepository.save(borrower);
    }

    /**
     * Get all borrow records for a borrower.
     */
    public List<BorrowRecord> getBorrowHistory(String borrowerId) {
        borrowerRepository.findById(borrowerId)
            .orElseThrow(() -> new BorrowerNotFoundException("Borrower with id " + borrowerId + " not found"));
        return borrowRecordRepository.findByBorrowerId(borrowerId);
    }

    /**
     * Get borrowers who currently have overdue books.
     */
    public List<Borrower> getOverdueBorrowers() {
    	return borrowRecordRepository.findOverdue(LocalDate.now());
    }
}