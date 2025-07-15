package com.jtim.borrow;

import java.time.LocalDate;
import java.util.List;

import com.jtim.books.Book;
import com.jtim.borrower.Borrower;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
<<<<<<< HEAD
=======
import jakarta.persistence.GenerationType;
>>>>>>> 1c30e4a (Reinitialized repo after .git folder removal)
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {

	@Id
<<<<<<< HEAD
	@GeneratedValue
=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> 1c30e4a (Reinitialized repo after .git folder removal)
	private Long id;

	@ManyToOne(optional = false)
	private Borrower borrower;

	@ManyToMany
	private List<Book> books;

	private LocalDate borrowDate;
}
