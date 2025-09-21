package org.spring.web.vaii.security;

import jakarta.persistence.EntityNotFoundException;
import org.spring.web.vaii.entity.Employee;
import org.spring.web.vaii.entity.WorkSchedule;
import org.spring.web.vaii.repository.EmployeeRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    private final EmployeeRepository employeeRepository;

    public CustomAuthProvider(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        Employee employee = this.employeeRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException());
        WorkSchedule workSchedule = employee.getWorkSchedule();

        boolean isNotLate = LocalTime.now().isBefore(workSchedule.getEndTime());

        if (!isNotLate) {
            throw new BadCredentialsException("You are too late to login.");
        }

        List<GrantedAuthority> authorities = Stream.concat(
                authentication.getAuthorities().stream(),
                Stream.of(new SimpleGrantedAuthority("ROLE_" + employee.getRole().name()))
        ).toList();

        return new GuardianAuthenticationToken(username, authorities, workSchedule.getEndTime());

    }

        @Override
        public boolean supports (Class < ? > authentication) {
            return GuardianAuthenticationToken.class.isAssignableFrom(authentication);
        }
    }
