package com.library.library_system.dto;

public class BookResponseDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int stock;
    public BookResponseDTO(Long id,String title,String author,String isbn,int stock){
        this.id=id;
        this.title=title;
        this.author=author;
        this.isbn=isbn;
        this.stock=stock;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getStock() {
        return stock;
    }
}
