package com.asiana.mall.vo;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String id;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String pwd;
    private String name;
    private String address;
    private String phone;
    private String email;
}