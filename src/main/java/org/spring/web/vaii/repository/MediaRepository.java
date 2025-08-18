package org.spring.web.vaii.repository;

import org.spring.web.vaii.DAY_TIME;
import org.spring.web.vaii.entity.image.Image;
import org.spring.web.vaii.entity.image.Media;
import org.spring.web.vaii.entity.image.Video;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class MediaRepository {
    private final JdbcTemplate jdbcTemplate;

    public MediaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public String findByName(String name) {
        String sql = "SELECT path FROM images WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, String.class);
    }

    public List<Media> findAllByDayTime(DAY_TIME dayTime) {
        String sql = "SELECT name, path, type, day_time FROM media WHERE day_time = ?";

        return jdbcTemplate.query(sql,
                ps -> ps.setString(1, dayTime.name()),
                (rs, rowNum) -> {
                    String name = rs.getString("name");
                    String path = rs.getString("path");
                    String type = rs.getString("type");
                    DAY_TIME dt = DAY_TIME.valueOf(rs.getString("day_time"));

                    switch (type.toUpperCase()) {
                        case "IMG":
                            return new Image(name, path, dt);
                        case "VIDEO":
                            return new Video(name, path, dt);
                        default:
                            throw new IllegalArgumentException("Unknown media type: " + type);
                    }

                });
    }

    public List<Media> findAll() {
        String sql = "SELECT name, path, type FROM media";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            String name = rs.getString("name");
            String path = rs.getString("path");
            String type = rs.getString("type");
            String day_time = rs.getString("day_time");

            if ("IMAGE".equalsIgnoreCase(type)) {
                return new Image(name, path, DAY_TIME.valueOf(day_time));
            } else if ("VIDEO".equalsIgnoreCase(type)) {
                return new Video(name, path, DAY_TIME.valueOf(day_time));
            } else {
                throw new IllegalArgumentException("Unknown media type: " + type);
            }
        });
    }
}
