package com.asiana.mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/ShopMiniMall/main").permitAll()
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
                .logout();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
    }
}
