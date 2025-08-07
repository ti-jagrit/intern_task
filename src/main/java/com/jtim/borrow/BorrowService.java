package com.jtim.borrow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jtim.books.Book;
import com.jtim.books.BookRepo;
import com.jtim.borrower.BorrowResponseDto;
import com.jtim.borrower.Borrower;
import com.jtim.borrower.BorrowerRepository;
import com.jtim.utils.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowService {

	private final BorrowRepository borrowRepository;
	private final BorrowerRepository borrowerRepository;
	private final BookRepo bookRepository;

	public ApiResponse<?> borrowBooks(BorrowRequestDto dto) {
		try {
			log.info("Borrow Book Requeest Recived: {}", dto);
			Borrower borrower = borrowerRepository.findById(dto.getBorrowerId())
					.orElseThrow(() -> new RuntimeException("Borrower not found"));

			Map<Long, Integer> bookQuantities = dto.getBookQuantities();

			if (bookQuantities == null || bookQuantities.isEmpty()) {
				throw new RuntimeException("No books selected for borrowing");
			}

			List<Book> booksToBorrow = new ArrayList<>();

			for (Map.Entry<Long, Integer> entry : bookQuantities.entrySet()) {
				Long bookId = entry.getKey();
				int requestedQty = entry.getValue();

				Book book = bookRepository.findById(bookId)
						.orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));

				if (book.getQuantity() < requestedQty) {
					return new ApiResponse<>(400, "insufficient quantity: " + book.getBookName(), null);

				}

				// Reduce stock
				book.setQuantity(book.getQuantity() - requestedQty);

				// Add to borrow list multiple times based on quantity
				for (int i = 0; i < requestedQty; i++) {
					booksToBorrow.add(book);
				}
			}

			// Save updated book quantities
			bookRepository.saveAll(booksToBorrow);

			Borrow borrow = new Borrow();
			borrow.setBorrower(borrower);
			borrow.setBooks(booksToBorrow);
			borrow.setBorrowDate(LocalDate.now());

			Borrow savedBorrow = borrowRepository.save(borrow);

			return new ApiResponse<>(201, "Books borrowed successfully", toDto(savedBorrow));

		} catch (RuntimeException e) {
			return new ApiResponse<>(400, e.getMessage(), null); // Send error message to frontend
		} catch (Exception e) {
			return new ApiResponse<>(500, "Something went wrong", null); // Generic fallback
		}
	}

	public ApiResponse<List<BorrowResponseDto>> getAllBorrows() {
		List<Borrow> borrows = borrowRepository.findAll();
		List<BorrowResponseDto> response = borrows.stream().map(this::toDto).toList();

		return new ApiResponse<>(200, "All borrow records", response);
	}

	public ApiResponse<List<BorrowResponseDto>> getBorrowsByBorrowerId(Long borrowerId) {
		List<Borrow> borrows = borrowRepository.findByBorrowerId(borrowerId);
		List<BorrowResponseDto> response = borrows.stream().map(this::toDto).toList();

		return new ApiResponse<>(200, "Borrows for borrower", response);
	}

	private BorrowResponseDto toDto(Borrow borrow) {
		List<BorrowResponseDto.BookInfo> bookInfos = borrow.getBooks().stream()
				.map(book -> new BorrowResponseDto.BookInfo(book.getId(), book.getBookName(), book.getAuthor()))
				.toList();

		return new BorrowResponseDto(borrow.getId(), borrow.getBorrowDate(), borrow.getBorrower().getId(),
				borrow.getBorrower().getBorrowerName(), borrow.getBorrower().getMobileNo(), bookInfos);
	}
}
