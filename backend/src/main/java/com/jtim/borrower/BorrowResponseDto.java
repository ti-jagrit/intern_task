package com.jtim.borrower;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowResponseDto {

    private Long borrowId;
    private LocalDate borrowDate;

    private Long borrowerId;
    private String borrowerName;
    private String mobileNo;

    private List<BookInfo> books;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BookInfo {
    	private Long bookId;
        private String bookName;
        private String author;
    }
}

