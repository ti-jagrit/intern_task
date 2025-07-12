package com.jtim.borrow;

import java.time.LocalDateTime;
import java.util.List;

import com.jtim.books.Book;
import com.jtim.borrowrs.Borrower;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Borrower borrower;

    @ManyToMany
    private List<Book> books;

    private LocalDateTime borrowDate;
}
