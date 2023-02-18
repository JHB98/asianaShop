package com.asiana.mall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.asiana.mall.repository.MemberMapper;
import com.asiana.mall.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberMapper memberMapper;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
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
