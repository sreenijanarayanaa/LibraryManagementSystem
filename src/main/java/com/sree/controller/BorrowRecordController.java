package com.sree.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sree.Exception.BookNotFoundException;
import com.sree.Exception.BorrowerNotFoundException;
import com.sree.Exception.InvalidOperationException;
import com.sree.dto.BorrowerDueDTO;
import com.sree.dto.BorrowerDueInfo;
import com.sree.entity.Book;
import com.sree.entity.BorrowRecord;
import com.sree.entity.Borrower;
import com.sree.entity.FinePolicy;
import com.sree.repository.BookRepository;
import com.sree.repository.BorrowRecordRepository;
import com.sree.repository.BorrowRepository;
import com.sree.repository.FinePolicyRepository;
import com.sree.service.BorrowRecordService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/records")
public class BorrowRecordController {

    @Autowired
    private BorrowRecordService borrowRecordService;

    // POST /records/borrow?borrowerId=...&bookId=...
    @PostMapping("/borrow/")
    public BorrowRecord borrowBook(@RequestParam String borrowerId, @RequestParam String bookId) {
        return borrowRecordService.borrowBook(borrowerId, bookId);
    }

    // POST /records/return?borrowerId=...&bookId=...
    @PostMapping("/return/")
    public BorrowRecord returnBook(@RequestParam String borrowerId, @RequestParam String bookId) {
        return borrowRecordService.returnBook(borrowerId, bookId);
    }

    // GET /records/active
    @GetMapping("/active/")
    public List<BorrowerDueDTO> getActiveRecords() {
        return borrowRecordService.getActiveRecords();
    }
}