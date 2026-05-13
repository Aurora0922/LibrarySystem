package com.library.library_system.entity;

import jakarta.persistence.*;

@Entity //标记为JAP实体，框架会自动管理
@Table(name="books") //指定映射的数据库表名为books

public class Book {
    @Id //标记为主键
    @GeneratedValue(strategy=GenerationType.IDENTITY) //主键自动递增
    private Long id;
    private String title; //书名
    private String author; //作者
    private String isbn; //ISBN编号
    private int stock; //库存数量

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
