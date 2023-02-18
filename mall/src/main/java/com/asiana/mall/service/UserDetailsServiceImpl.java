package com.asiana.mall.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.asiana.mall.repository.MemberMapper;
import com.asiana.mall.vo.Member;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private MemberMapper memberMapper;

    public UserDetailsServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member user = memberMapper.selectMemberById(id);

        if (user == null) {
            return null;
        }

        String pwd = user.getPwd();
        String roles = "user";

        return User.builder()
                .username(id)
                .password(pwd)
                .roles(roles)
                .build();
    }
}
