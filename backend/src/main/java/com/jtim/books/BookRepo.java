package com.jtim.books;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepo extends JpaRepository<Book, Long> {
	@Query("SELECT b FROM Book b WHERE LOWER(b.bookName) LIKE LOWER(CONCAT('%', :keyword, '%')) "
			+ "OR LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	Page<Book> search(String keyword, Pageable pageable);

}
