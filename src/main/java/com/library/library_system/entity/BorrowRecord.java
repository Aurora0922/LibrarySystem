package com.library.library_system.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="borrow_records")
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //多条借阅记录对应一个读者
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    //多条节约记录对应一本书
    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    private LocalDate borrowDate;//借阅日期
    private LocalDate returnDate;//归还日期，null表示未还

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
