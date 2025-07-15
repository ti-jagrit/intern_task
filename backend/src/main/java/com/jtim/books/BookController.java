package com.jtim.books;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	@RequestMapping("/api/books")
	@RequiredArgsConstructor
	public class BookController {

		@Autowired
	    private BookService bookService;

	    @GetMapping
	    public ResponseEntity<ApiResponse<Map<String,Object>>> getAllBooks(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "5") int size,
	            @RequestParam(required = false) String keyword
	    )throws Exception {
	        return ResponseEntity.ok(bookService.getAllBooks(page, size, keyword));
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable Long id) throws Exception{
	        return ResponseEntity.ok(bookService.getBookById(id));
	    }

	    @PostMapping
	    public ResponseEntity<ApiResponse<Book>> createBook(@RequestBody Book book) throws Exception{
	        return ResponseEntity.ok(bookService.saveBook(book));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<ApiResponse<Book>> updateBook(@PathVariable Long id, @RequestBody Book book) throws Exception{
<<<<<<< HEAD
	        return ResponseEntity.ok(bookService.updateBook(id, book));
=======
	        return ResponseEntity.ok(bookService.updateBook(id,book));
>>>>>>> 1c30e4a (Reinitialized repo after .git folder removal)
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Long id) throws Exception{
	        return ResponseEntity.ok(bookService.deleteBook(id));
	    }
	


}
