package com.example.service;

import com.shop.project.vo.Member;

import java.util.List;

public interface MemberService {

    List<Member> getMember(Member member);

    void postMember(Member member);

}
