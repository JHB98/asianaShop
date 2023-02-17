package com.example.service;

import com.shop.project.repository.MemberMapper;
import com.shop.project.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberMapper memberMapper;

    @Autowired
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
