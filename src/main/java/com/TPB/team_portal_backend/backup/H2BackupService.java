package com.TPB.team_portal_backend.backup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class H2BackupService {

    private final JdbcTemplate jdbcTemplate;

    private static final Path BACKUP_DIR = Path.of("backup", "db");
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd-HHmm");

    @Autowired
    public H2BackupService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // For testing: run every 30 seconds
    // @Scheduled(cron = "*/30 * * * * *")
    // For daily backup at 2 AM, use:
    @Scheduled(cron = "0 0 2 * * *")
    public void backupDatabase() {
        try {
            Files.createDirectories(BACKUP_DIR);

            String timestamp = LocalDateTime.now().format(FORMATTER);
            Path backupFile = BACKUP_DIR.resolve("ideadb-" + timestamp + ".zip");

            // H2 expects forward slashes in path
            String h2Path = backupFile.toAbsolutePath().toString().replace("\\", "/");

            String sql = "BACKUP TO '" + h2Path + "'";
            jdbcTemplate.execute(sql);

            System.out.println("✅ H2 backup created at: " + backupFile.toAbsolutePath());
        } catch (Exception e) {
            System.err.println("❌ H2 backup failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
