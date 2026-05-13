package com.library.library_system.repository;

import com.library.library_system.entity.BorrowRecord;
import com.library.library_system.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    // 按读者查借阅记录（Spring 自动实现，方法名就是查询条件）
    List<BorrowRecord> findByMember(Member member);

    // 查某读者未还的记录（returnDate 为 null = 未还）
    List<BorrowRecord> findByMemberAndReturnDateIsNull(Member member);
}