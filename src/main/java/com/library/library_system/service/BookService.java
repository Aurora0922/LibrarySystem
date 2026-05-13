package com.library.library_system.service;

import com.library.library_system.dto.BookRequestDTO;
import com.library.library_system.dto.BookResponseDTO;
import com.library.library_system.entity.Book;
import com.library.library_system.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    private BookResponseDTO toDTO(Book book){
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getStock()
        );
    }

    //查询所有图书
    public List<BookResponseDTO> getAllBooks(){
        return bookRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public BookResponseDTO addBook(BookRequestDTO dto){
        Book book=new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setStock(dto.getStock());
        return toDTO(bookRepository.save(book));
    }
    //新增图书
    public BookResponseDTO updateBook(Long id, BookRequestDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("图书不存在，id: " + id));
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setStock(dto.getStock());
        return toDTO(bookRepository.save(book));
    }
    //删除
    public void deleteBook(Long id){
        if(!bookRepository.existsById(id)){
            throw new RuntimeException("图书不存在，id："+id);
        }
        bookRepository.deleteById(id);
    }
}
