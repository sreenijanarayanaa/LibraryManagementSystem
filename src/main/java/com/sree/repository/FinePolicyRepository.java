package com.sree.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sree.entity.Borrower;
import com.sree.entity.FinePolicy;

public interface FinePolicyRepository extends JpaRepository<FinePolicy, String> {

	Optional<Borrower> findByCategory(String category);

}
