package com.jtim.borrow;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jtim.borrower.BorrowResponseDto;
import com.jtim.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/borrows")
@RequiredArgsConstructor
public class BorrowController {

	private final BorrowService borrowService;

	@PostMapping
	public ResponseEntity<ApiResponse<?>> borrowBooks(@RequestBody BorrowRequestDto dto) {
		ApiResponse<?> response = borrowService.borrowBooks(dto);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<BorrowResponseDto>>> getAllBorrows() {
		ApiResponse<List<BorrowResponseDto>> response = borrowService.getAllBorrows();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@GetMapping("/borrower/{id}")
	public ResponseEntity<ApiResponse<List<BorrowResponseDto>>> getBorrowsByBorrower(@PathVariable Long id) {
		return ResponseEntity.ok(borrowService.getBorrowsByBorrowerId(id));
	}
}
