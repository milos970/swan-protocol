package org.spring.web.vaii.security;

import jakarta.servlet.http.HttpSession;
import org.spring.web.vaii.sevice.CustomSessionManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class ProjectConfig {

    @Autowired
    private CustomSessionManagement customSessionManagement;

    private final String[] whiteList = {
            "/css/**",
            "/js/**",
            "/files/images/**",
            "/files/videos/**",
            "/",
            "/login",
            "/population-preview",
            "/login-page",
            "/submit",
            "get-time"
    };
    private final String[] blackList = {"/admin/**"};


    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.sessionManagement(session -> session
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true) // zabráni novému prihláseniu
                )
                .addFilterAfter(customFilter(customSessionManagement), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/ws/**").permitAll()   // povolí WebSocket handshake
                        .requestMatchers("/topic/**").permitAll()
                        .requestMatchers(whiteList).permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")                  // GET /login -> zobrazí login.html
                        .loginProcessingUrl("/login")         // POST /login spracuje Spring Security
                        .defaultSuccessUrl("/user/home", true)// kam presmerovať po úspechu
                        .failureUrl("/login?error=true")

                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .addLogoutHandler((request, response, authentication) -> {
                            HttpSession session = request.getSession(false);
                            customSessionManagement.removeSessionId(session.getId());
                        })
                        .logoutSuccessUrl("/")
                        .permitAll()


                );

        return http.build();
    }


    @Bean
    JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM users WHERE username = ?");
        return manager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CustomFilter customFilter(CustomSessionManagement sessionManagement) {
        return new CustomFilter(sessionManagement);
    }

}
