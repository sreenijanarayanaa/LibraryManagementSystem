package com.sree.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sree.entity.BorrowRecord;
import com.sree.entity.Borrower;
import com.sree.service.BorrowerService;

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {
	
	@Autowired
    private  BorrowerService borrowerService;
    
    //● POST /borrowers – Register borrower. 
    @PostMapping
    public ResponseEntity<Borrower> registerBorrower(@RequestBody Borrower borrower) {
        return ResponseEntity.ok(borrowerService.registerBorrower(borrower));
    }

    //● GET /borrowers/{id}/records – Get all borrow history. 
    @GetMapping("/{id}/records")
    public ResponseEntity<List<BorrowRecord>> getBorrowHistory(@PathVariable String id) {
        return ResponseEntity.ok(borrowerService.getBorrowHistory(id));
    }
    // ● GET /borrowers/overdue 
    @GetMapping("/overdue")
    public ResponseEntity<List<Borrower>> getOverdueBorrowers() {
        return ResponseEntity.ok(borrowerService.getOverdueBorrowers());
    }
}
