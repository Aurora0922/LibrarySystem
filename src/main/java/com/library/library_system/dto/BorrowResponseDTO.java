package com.library.library_system.dto;

import java.time.LocalDate;

public class BorrowResponseDTO {
    private Long id;
    private String memberName;  // 读者姓名
    private String bookTitle;   // 书名
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String status;      // "借阅中" 或 "已归还"

    public BorrowResponseDTO(Long id, String memberName, String bookTitle,
                             LocalDate borrowDate, LocalDate returnDate) {
        this.id = id;
        this.memberName = memberName;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = (returnDate == null) ? "借阅中" : "已归还";
    }

    public Long getId() { return id; }
    public String getMemberName() { return memberName; }
    public String getBookTitle() { return bookTitle; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public String getStatus() { return status; }
}