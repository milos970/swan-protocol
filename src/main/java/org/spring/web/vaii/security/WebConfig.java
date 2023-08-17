package org.spring.web.vaii.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter
{
    private final String[] whiteList = {"/resources/**", "/*", "/page_a", "/login-page", "/", "/check-email","/check-username"};
    private final String[] blackList = {"/admin/**"};

    @Autowired
    UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {  // (2)
        http

                .authorizeRequests()
                .antMatchers(whiteList).permitAll()
                .antMatchers(blackList).hasAuthority("ADMIN")


                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/redirect")
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();

        // It is generally BAD to disable CSRF protection!
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder(10, new SecureRandom())); //salting
        return provider;
    }



















}
