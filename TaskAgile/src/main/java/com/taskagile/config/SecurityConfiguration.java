package com.taskagile.config;

import com.taskagile.web.apis.authenticate.AuthenticationFilter;
import com.taskagile.web.apis.authenticate.SimpleAuthenticationFailureHandler;
import com.taskagile.web.apis.authenticate.SimpleAuthenticationSuccessHandler;
import com.taskagile.web.apis.authenticate.SimpleLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String[] PUBLIC = new String[]{
            "/error", "/login", "/logout", "/register", "/api/registrations"
    };

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(PUBLIC).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler())
                .logoutSuccessUrl("/login?logged-out")
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/css/**","/static/**", "/js/**", "/images/**", "/favicon.ico");
    }

    @Bean
    public AuthenticationFilter authenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationFilter;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new SimpleAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new SimpleAuthenticationFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new SimpleLogoutSuccessHandler();
    }
}