package com.asiana.mall.service;

import java.util.List;

import com.asiana.mall.vo.Member;

public interface MemberService {

    List<Member> getMember(Member member);

    void postMember(Member member);

}
