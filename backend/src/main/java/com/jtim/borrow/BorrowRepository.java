package com.jtim.borrow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    List<Borrow> findByBorrowerId(Long borrowerId);
}

