package com.sree.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

import jakarta.transaction.Transactional;

@Service
public class BorrowRecordService {
	@Autowired
    private BookRepository bookRepo;

    @Autowired
    private BorrowRepository borrowerRepo;

    @Autowired
    private BorrowRecordRepository recordRepo;

    @Autowired
    private FinePolicyRepository fineRepo;

    @Transactional
    public BorrowRecord borrowBook(String borrowerId, String bookId) {
        Borrower borrower = borrowerRepo.findById(borrowerId)
                .orElseThrow(() -> new BorrowerNotFoundException("Borrower not found"));
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));

        long activeCount = borrower.getBorrowRecords().stream()
                .filter(br -> br.getReturnDate() == null)
                .count();

        if (activeCount >= borrower.getMaxBorrowLimit())
            throw new InvalidOperationException("Borrow limit exceeded");

        if (book.getAvailableCopies() <= 0)
            throw new InvalidOperationException("No copies available");

        // update book copies
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        book.setAvailable(book.getAvailableCopies() > 0);
        bookRepo.save(book);

        // create borrow record
        BorrowRecord record = new BorrowRecord();
        record.setBook(book);
        record.setBorrower(borrower);
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(14));
        record.setFineAmount(0.0);

        return recordRepo.save(record);
    }

    @Transactional
    public BorrowRecord returnBook(String borrowerId, String bookId) {
        List<BorrowRecord> active = recordRepo.findActiveRecordsAll();

        BorrowRecord found = active.stream()
                .filter(r -> r.getBorrower().getId().equals(borrowerId)
                        && r.getBook().getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new InvalidOperationException("No active borrow record found"));

        found.setReturnDate(LocalDate.now());

        // compute fine
        if (found.getReturnDate().isAfter(found.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(found.getDueDate(), found.getReturnDate());
            double finePerDay = 10.0;
                    
                   
            found.setFineAmount(daysLate * finePerDay);
        } else {
            found.setFineAmount(0.0);
        }

        // update book availability
        Book book = found.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        book.setAvailable(true);
        bookRepo.save(book);

        return recordRepo.save(found);
    }

    public List<BorrowerDueDTO> getActiveRecords() {
        return recordRepo.findActiveRecords();
    }
}