package com.asiana.mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.asiana.mall.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    UserDetailsServiceImpl userDetailsServiceImpl;

    public SpringSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // ajax post 시에 권한 관련 
        http
                .authorizeRequests()
                .antMatchers("/ShopMiniMall/main",
                        "/ShopMiniMall/MemberUIServlet",
                        "/ShopMiniMall/membership",
                        "/ShopMiniMall/Message",
                        "/ShopMiniMall/flight",
                        "/ShopMiniMall/product/{number}",
                        "/ShopMiniMall/product/category/**",
                        "/ShopMiniMall/member/{id}")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/ShopMiniMall/member")
                .loginProcessingUrl("/ShopMiniMall/login")
                .usernameParameter("id")
                .passwordParameter("pwd")
                .defaultSuccessUrl("/ShopMiniMall/main", true)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/ShopMiniMall/main") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/assets/**", "/img/**", "/js/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl);
    }
}