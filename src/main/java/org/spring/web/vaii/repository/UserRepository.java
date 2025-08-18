package org.spring.web.vaii.repository;

import org.spring.web.vaii.Authority;
import org.spring.web.vaii.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepository
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findUserByUsername(String username) {
        String sql = "SELECT id, username, email, password, enabled FROM users WHERE username = ?";
        return this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
    }


    public int numberOfUsers() {
        String sql = "SELECT COUNT(*) FROM users WHERE authority = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, Authority.ROLE_USER.name());
    }

    public void createUser(String username, String password, String email) {
        String sql = "INSERT INTO users (username, email, password, authority, enabled) VALUES (?, ?, ?, ?,?)";
        this.jdbcTemplate.update(sql, username, email, password, Authority.ROLE_USER.name(),true);
    }


}