package com.jtim.books;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jtim.utils.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    @Autowired
	private  BookRepo bookRepository;

    public ApiResponse<Map<String, Object>> getAllBooks(int page, int size, String keyword) {
        log.info("get all books service method called");
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("bookName").ascending());
        Page<Book> books;

        if (keyword != null && !keyword.trim().isEmpty()) {
            books = bookRepository.search(keyword, pageable);
        } else {
            books = bookRepository.findAll(pageable);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("content", books.getContent());
        response.put("pageNumber", books.getNumber());
        response.put("pageSize", books.getSize());
        response.put("totalElements", books.getTotalElements());
        response.put("totalPages", books.getTotalPages());
        response.put("last", books.isLast());

        return new ApiResponse<>(200, "Books fetched", response);
    }

    public ApiResponse<Book> getBookById(Long id) {
    	log.info("get book by id service method called");

    	Book book = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found"));
        
        return new ApiResponse<>(200, "Book found", book);
    }

    public ApiResponse<Book> saveBook(Book book) {
    	log.info("Book Store method called");

        return new ApiResponse<>(201, "Book saved", bookRepository.save(book));
    }

    public ApiResponse<Book> updateBook(Long id, Book updated) {
    	log.info("id: {}  Book updating",id);
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setBookName(updated.getBookName());
        book.setAuthor(updated.getAuthor());
        book.setPrice(updated.getPrice());
        book.setQuantity(updated.getQuantity());
        return new ApiResponse<>(200, "Book updated", bookRepository.save(book));
    }

    public ApiResponse<Void> deleteBook(Long id) {
    	log.info(" Deleting book with id: {}",id);

        bookRepository.deleteById(id);
        return new ApiResponse<>(200, "Book deleted", null);
    }
}

