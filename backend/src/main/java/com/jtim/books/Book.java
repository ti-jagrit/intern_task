package com.jtim.books;

<<<<<<< HEAD
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
=======
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
>>>>>>> 1c30e4a (Reinitialized repo after .git folder removal)
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
<<<<<<< HEAD
    @Id @GeneratedValue
    private Long id;
    private String bookName;
    private String author;
    private BigDecimal price;
=======
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookName;
    private String author;

    
    private Double price;
>>>>>>> 1c30e4a (Reinitialized repo after .git folder removal)
    private Integer quantity;
}
