package com.jtim.borrow;

import java.util.List;

import lombok.Data;

@Data
public class BorrowRequestDto {
    private Long borrowerId;
    private List<Long> bookIds;
}
