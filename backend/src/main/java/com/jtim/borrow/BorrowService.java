package com.jtim.borrow;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jtim.books.Book;
import com.jtim.books.BookRepo;
import com.jtim.borrower.Borrower;
import com.jtim.borrower.BorrowerRepository;
import com.jtim.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BorrowerRepository borrowerRepository;
    private final BookRepo bookRepository;

    public ApiResponse<Borrow> borrowBooks(BorrowRequestDto dto) {
        Borrower borrower = borrowerRepository.findById(dto.getBorrowerId())
                .orElseThrow(() -> new RuntimeException("Borrower not found"));

        List<Book> books = bookRepository.findAllById(dto.getBookIds());
        if (books.isEmpty()) throw new RuntimeException("No books found for the given IDs");

        for (Book book : books) {
            if (book.getQuantity() <= 0) {
                throw new RuntimeException("Book out of stock: " + book.getBookName());
            }
            book.setQuantity(book.getQuantity() - 1);
        }
        bookRepository.saveAll(books);

        Borrow borrow = new Borrow();
        borrow.setBorrower(borrower);
        borrow.setBooks(books);
        borrow.setBorrowDate(LocalDate.now());

        return new ApiResponse<>(201, "Books borrowed successfully", borrowRepository.save(borrow));
    }

    public ApiResponse<List<Borrow>> getAllBorrows() {
        return new ApiResponse<>(200, "All borrow records", borrowRepository.findAll());
    }

    public ApiResponse<List<Borrow>> getBorrowsByBorrowerId(Long borrowerId) {
        return new ApiResponse<>(200, "Borrows for borrower", borrowRepository.findByBorrowerId(borrowerId));
    }
}
