package com.project.lab.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
@Slf4j
public class DatabaseConnection {
    private final DataSource dataSource;

    public void run(String... args) {
        log.info("Attempting to connect to the database...");

        try {
            dataSource.getConnection();
            log.info("Database connection established successfully.");
        }
        catch (SQLException e) {
            log.error("Failed to connect to the database: {}", e.getMessage());
        }
    }
}