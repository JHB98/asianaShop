package com.asiana.mall.vo;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String id;
    @AssertTrue(message = "아이디 중복 확인을 해주세요.")
    private boolean idCheck;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String pwd;
    @AssertTrue(message = "비밀번호 확인을 해주세요.")
    private boolean pwdCheck;
    private String name;
    private String address;
    private String phone;
    private String email;
}