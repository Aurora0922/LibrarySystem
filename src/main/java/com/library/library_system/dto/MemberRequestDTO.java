package com.library.library_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MemberRequestDTO {

    @NotBlank(message="读者姓名不能为空")
    private String name;
    @NotBlank(message="手机号不能为空")
    @Pattern(regexp="^1[3-9]\\d{9}$",message="手机号码格式不正确")
    private String phone;
    private String email;
}
