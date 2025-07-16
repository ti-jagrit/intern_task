package com.jtim.borrower;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
	@Query("SELECT b FROM Borrower b WHERE LOWER(b.borrowerName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	Page<Borrower> search(String keyword, Pageable pageable);
}
