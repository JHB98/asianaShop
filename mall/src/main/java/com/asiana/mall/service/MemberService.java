package com.asiana.mall.service;

import java.util.List;
import com.asiana.mall.vo.Member;

public interface MemberService {

    List<Member> getMember(Member member);

    Member getMemberById(String id);

    void postMember(Member member);

    void putMember(String id, Member member);

}
