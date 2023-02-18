package com.asiana.mall.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.asiana.mall.repository.MemberMapper;
import com.asiana.mall.vo.Member;
import java.util.Map;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    // 회원가입 시, 유효성 체크
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }

    @Override
    public List<Member> getMember(Member member) {
        return memberMapper.selectMember(member);
    }

    @Override
    public void postMember(Member member) {
        memberMapper.insertMember(member);
    }
}
