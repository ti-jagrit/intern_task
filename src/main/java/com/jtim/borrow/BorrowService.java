package com.jtim.borrow;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jtim.books.Book;
import com.jtim.books.BookRepo;
import com.jtim.borrower.BorrowResponseDto;
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

    public ApiResponse<BorrowResponseDto> borrowBooks(BorrowRequestDto dto) {
        Borrower borrower = borrowerRepository.findById(dto.getBorrowerId())
                .orElseThrow(() -> new RuntimeException("Borrower not found"));

        List<Book> books = bookRepository.findAllById(dto.getBookIds());
        if (books.isEmpty()) {
            throw new RuntimeException("No books found for the given IDs");
        }

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

        borrow.setId(null);

        Borrow savedBorrow = borrowRepository.save(borrow);
        return new ApiResponse<>(201, "Books borrowed successfully", toDto(savedBorrow));
    }


    public ApiResponse<List<BorrowResponseDto>> getAllBorrows() {
        List<Borrow> borrows = borrowRepository.findAll();
        List<BorrowResponseDto> response = borrows.stream()
                .map(this::toDto)
                .toList();

        return new ApiResponse<>(200, "All borrow records", response);
    }


    public ApiResponse<List<BorrowResponseDto>> getBorrowsByBorrowerId(Long borrowerId) {
        List<Borrow> borrows = borrowRepository.findByBorrowerId(borrowerId);
        List<BorrowResponseDto> response = borrows.stream()
                .map(this::toDto)
                .toList();

        return new ApiResponse<>(200, "Borrows for borrower", response);
    }

	private BorrowResponseDto toDto(Borrow borrow) {
	    List<BorrowResponseDto.BookInfo> bookInfos = borrow.getBooks().stream()
	            .map(book -> new BorrowResponseDto.BookInfo( book.getId(), book.getBookName(), book.getAuthor()))
	            .toList();

	    return new BorrowResponseDto(
	            borrow.getId(),
	            borrow.getBorrowDate(),
	            borrow.getBorrower().getId(),
	            borrow.getBorrower().getBorrowerName(),
	            borrow.getBorrower().getMobileNo(),
	            bookInfos
	    );
	}
}
