package com.jtim.borrower;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jtim.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/borrowers")
@RequiredArgsConstructor
public class BorrowerController {

    private final BorrowerService borrowerService;

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> getAllBorrowers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String keyword
    ) {
        return ResponseEntity.ok(borrowerService.getAllBorrowers(page, size, keyword));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Borrower>> getBorrowerById(@PathVariable Long id) {
        return ResponseEntity.ok(borrowerService.getBorrowerById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Borrower>> createBorrower(@RequestBody Borrower borrower) {
        return ResponseEntity.ok(borrowerService.saveBorrower(borrower));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Borrower>> updateBorrower(@PathVariable Long id, @RequestBody Borrower borrower) {
        return ResponseEntity.ok(borrowerService.updateBorrower(id, borrower));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBorrower(@PathVariable Long id) {
        return ResponseEntity.ok(borrowerService.deleteBorrower(id));
    }
}
