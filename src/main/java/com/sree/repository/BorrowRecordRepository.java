package com.sree.repository;

import java.util.UUID;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sree.dto.BorrowerDueDTO;
import com.sree.dto.BorrowerDueInfo;
import com.sree.entity.BorrowRecord;
import com.sree.entity.Borrower;
import com.sree.dto.BorrowerDueInfo;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, String> {

	List<BorrowRecord> findByBorrowerId(String borrowerId);


	@Query("select br from BorrowRecord br where br.returnDate is null and br.dueDate < :today")
	List<Borrower> findOverdue(LocalDate today);

	/*
	 * @Query(""" select new com.sree.dto.BorrowerDueDTO( b.name, br.dueDate ) from
	 * BorrowRecord br join br.borrower b where br.returnDate is null """)
	 */	
	
	@Query("""
		    select new com.sree.dto.BorrowerDueDTO(
		        b.name,
		        bk.title,
		        br.dueDate
		    )
		    from BorrowRecord br
		    join br.borrower b
		    join br.book bk
		    where br.returnDate is null
		""")
	List<BorrowerDueDTO> findActiveRecords();

		/*
		 * @Query("select b.name,br from BorrowRecord br join Borrower b on br.borrower_id =  b.id where br.returnDate is null"
		 * ) List<BorrowRecord> findActiveRecords();
		 */


	boolean existsByBookIdAndReturnDateIsNull(String bookId);

	@Query("select br from BorrowRecord br where br.returnDate is null")
	List<BorrowRecord> findActiveRecordsAll();

	
}
