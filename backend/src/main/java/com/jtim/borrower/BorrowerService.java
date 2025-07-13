package com.jtim.borrower;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jtim.utils.ApiResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BorrowerService {

	private final BorrowerRepository borrowerRepository;

	public ApiResponse<Map<String, Object>> getAllBorrowers(int page, int size, String keyword) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("borrowerName").ascending());
		Page<Borrower> borrowers;

		if (keyword != null && !keyword.trim().isEmpty()) {
			borrowers = borrowerRepository.search(keyword, pageable);
		} else {
			borrowers = borrowerRepository.findAll(pageable);
		}

		Map<String, Object> response = new HashMap<>();
		response.put("content", borrowers.getContent());
		response.put("pageNumber", borrowers.getNumber());
		response.put("pageSize", borrowers.getSize());
		response.put("totalElements", borrowers.getTotalElements());
		response.put("totalPages", borrowers.getTotalPages());
		response.put("last", borrowers.isLast());

		return new ApiResponse<>(200, "Borrowers fetched", response);
	}

	public ApiResponse<Borrower> getBorrowerById(Long id) {
		Borrower borrower = borrowerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Borrower not found"));
		return new ApiResponse<>(200, "Borrower found", borrower);
	}

	public ApiResponse<Borrower> saveBorrower(Borrower borrower) {
		return new ApiResponse<>(201, "Borrower saved", borrowerRepository.save(borrower));
	}

	public ApiResponse<Borrower> updateBorrower(Long id, Borrower updated) {
		Borrower borrower = borrowerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Borrower not found"));

		borrower.setBorrowerName(updated.getBorrowerName());
		borrower.setAddress(updated.getAddress());
		borrower.setMobileNo(updated.getMobileNo());
		borrower.setEmail(updated.getEmail());

		return new ApiResponse<>(200, "Borrower updated", borrowerRepository.save(borrower));
	}

	public ApiResponse<Void> deleteBorrower(Long id) {
		borrowerRepository.deleteById(id);
		return new ApiResponse<>(200, "Borrower deleted", null);
	}
	


}
