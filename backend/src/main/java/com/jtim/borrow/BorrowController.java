package com.jtim.borrow;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jtim.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/borrows")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @PostMapping
    public ResponseEntity<ApiResponse<Borrow>> borrowBooks(@RequestBody BorrowRequestDto dto) {
        return ResponseEntity.ok(borrowService.borrowBooks(dto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Borrow>>> getAllBorrows() {
        return ResponseEntity.ok(borrowService.getAllBorrows());
    }

    @GetMapping("/borrower/{id}")
    public ResponseEntity<ApiResponse<List<Borrow>>> getBorrowsByBorrower(@PathVariable Long id) {
        return ResponseEntity.ok(borrowService.getBorrowsByBorrowerId(id));
    }
}
