package org.spring.web.vaii.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;
import static org.springframework.security.authorization.AuthorizationManagers.allOf;

@Configuration
public class ProjectConfig {

    @Autowired
    private CustomFilter customFilter;

    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    private final String[] whiteList = {
            "/css/**",
            "/js/**",
            "/files/images/**",
            "/files/videos/**",
            "/",
            "/login",
            "/population-preview",
            "/nature-preview",
            "/login-page",
            "/submit",
            "/work",
            "/check-email",
            "/check-username",
            "/ws/**",
            "/topic/**"
    };




    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF je zbytočné pre stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAfter(customFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .requestMatchers("/pages/jobs/countdown-timer/**").access(allOf(hasRole("USER"), hasRole("GUARDIAN")))
                        .requestMatchers(whiteList).permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(customSuccessHandler)
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationManager customAuthManager(CustomAuthProvider customAuthProvider) {
        return new ProviderManager(customAuthProvider);
    }



    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
