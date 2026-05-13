package com.library.library_system.service;

import com.library.library_system.dto.BorrowRequestDTO;
import com.library.library_system.dto.BorrowResponseDTO;
import com.library.library_system.entity.Book;
import com.library.library_system.entity.BorrowRecord;
import com.library.library_system.entity.Member;
import com.library.library_system.repository.BookRepository;
import com.library.library_system.repository.BorrowRecordRepository;
import com.library.library_system.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;

    // Entity 转 DTO
    private BorrowResponseDTO toDTO(BorrowRecord record) {
        return new BorrowResponseDTO(
                record.getId(),
                record.getMember().getName(),
                record.getBook().getTitle(),
                record.getBorrowDate(),
                record.getReturnDate()
        );
    }

    // 借书
    public BorrowResponseDTO borrowBook(BorrowRequestDTO dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new RuntimeException("读者不存在"));

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("图书不存在"));

        // 检查库存
        if (book.getStock() <= 0) {
            throw new RuntimeException("该书库存不足，无法借阅");
        }

        // 库存 -1
        book.setStock(book.getStock() - 1);
        bookRepository.save(book);

        // 创建借阅记录
        BorrowRecord record = new BorrowRecord();
        record.setMember(member);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setReturnDate(null);  // 未还

        return toDTO(borrowRecordRepository.save(record));
    }

    // 还书
    public BorrowResponseDTO returnBook(Long recordId) {
        BorrowRecord record = borrowRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("借阅记录不存在"));

        if (record.getReturnDate() != null) {
            throw new RuntimeException("该书已经归还过了");
        }

        // 库存 +1
        Book book = record.getBook();
        book.setStock(book.getStock() + 1);
        bookRepository.save(book);

        // 记录归还日期
        record.setReturnDate(LocalDate.now());
        return toDTO(borrowRecordRepository.save(record));
    }

    // 查询某读者的所有借阅记录
    public List<BorrowResponseDTO> getMemberRecords(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("读者不存在"));

        return borrowRecordRepository.findByMember(member)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // 查询所有未还记录
    public List<BorrowResponseDTO> getAllUnreturned() {
        return borrowRecordRepository.findAll()
                .stream()
                .filter(r -> r.getReturnDate() == null)
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}