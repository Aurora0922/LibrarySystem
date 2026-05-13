package com.library.library_system.repository;

import com.library.library_system.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookRepository extends JpaRepository<Book,Long>{
    // JpaRepository 已经实现了：
    // findAll() 查所有
    // findById() 按id查
    // save() 新增/修改
    // deleteById() 删除
}
