package com.library.library_system.repository;

import com.library.library_system.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByPhone(String phone);
    boolean existsByPhone(String phone);
}
