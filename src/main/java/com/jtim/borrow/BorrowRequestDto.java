package com.jtim.borrow;

import java.util.Map;

import lombok.Data;

@Data
public class BorrowRequestDto {
    private Long borrowerId;
    private Map<Long, Integer> bookQuantities;}
