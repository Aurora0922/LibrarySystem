package com.library.library_system.controller;

import com.library.library_system.dto.BorrowRequestDTO;
import com.library.library_system.dto.BorrowResponseDTO;
import com.library.library_system.service.BorrowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "借阅管理", description = "借书、还书、查借阅记录")
@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    // POST /api/borrow — 借书
    @Operation(summary="借书")
    @PostMapping
    public BorrowResponseDTO borrowBook(@RequestBody BorrowRequestDTO dto) {
        return borrowService.borrowBook(dto);
    }

    // PUT /api/borrow/1/return — 还书
    @Operation(summary="还书")
    @PutMapping("/{recordId}/return")
    public BorrowResponseDTO returnBook(@PathVariable Long recordId) {
        return borrowService.returnBook(recordId);
    }

    // GET /api/borrow/member/1 — 查某读者借阅记录
    @Operation(summary="查询某读者的借阅记录")
    @GetMapping("/member/{memberId}")
    public List<BorrowResponseDTO> getMemberRecords(@PathVariable Long memberId) {
        return borrowService.getMemberRecords(memberId);
    }

    // GET /api/borrow/unreturned — 查所有未还记录
    @Operation(summary="查询所有未还记录")
    @GetMapping("/unreturned")
    public List<BorrowResponseDTO> getAllUnreturned() {
        return borrowService.getAllUnreturned();
    }
}