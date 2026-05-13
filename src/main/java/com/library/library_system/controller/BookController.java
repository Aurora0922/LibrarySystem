package com.library.library_system.controller;

import com.library.library_system.dto.BookRequestDTO;
import com.library.library_system.dto.BookResponseDTO;
import com.library.library_system.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "图书管理", description = "图书的增删改查")
@RestController//返回json数据的控制器
@RequestMapping("/api/books")//请求路径前缀
public class BookController {

    @Autowired//spring自动吧BookService注入进来
    private BookService bookService;

    @Operation(summary = "查询所有图书")
    @GetMapping
    public List<BookResponseDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Operation(summary = "新增图书")
    @PostMapping
    public BookResponseDTO addBook(@RequestBody BookRequestDTO dto) {
        return bookService.addBook(dto);
    }

    @Operation(summary = "修改图书")
    @PutMapping("/{id}")
    public BookResponseDTO updateBook(@PathVariable Long id, @RequestBody BookRequestDTO dto) {
        return bookService.updateBook(id, dto);
    }

    @Operation(summary = "删除图书")
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "删除成功";
    }
}