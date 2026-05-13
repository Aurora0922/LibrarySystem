package com.library.library_system.dto;

import java.time.LocalDateTime;

public class MemberResponseDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private LocalDateTime createTime;

    public MemberResponseDTO(Long id, String name, String phone, String email, LocalDateTime createTime) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }
}
