package com.TPB.team_portal_backend.util;

import com.TPB.team_portal_backend.model.Idea;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExcelExporter {

    // Folder relative to where the app runs (project root in dev, jar dir in prod)
    private static final Path BACKUP_DIR = Path.of("backup");

    private static final String CURRENT_FILE_NAME = "Ideas_Current.xlsx";
    private static final String BACKUP_FILE_NAME = "Ideas_Backup.xlsx";
    private static final String AUDIT_FILE_NAME   = "Ideas_Audit.xlsx";

    private static final String[] HEADERS = {
            "Start time", "Completion time", "Email", "Name", "Toil/Improvement Item",
            "Description", "Impacted Component(s)", "Pain Points/Challenges", "Frequency",
            "Manual Effort (Time Spent)", "Automatable", "Impact(if automated)",
            "Solution", "Status", "Comments", "Type of ask", "Category",
            "Priority", "Complexity", "Suggested Priority", "US Direction",
            "Owner to create US", "Jira Created"
    };

    // For the history/audit file: add ID, Record Status, Audit Timestamp
    private static final String[] AUDIT_HEADERS = {
            "ID", "Start time", "Completion time", "Email", "Name", "Toil/Improvement Item",
            "Description", "Impacted Component(s)", "Pain Points/Challenges", "Frequency",
            "Manual Effort (Time Spent)", "Automatable", "Impact(if automated)",
            "Solution", "Status", "Comments", "Type of ask", "Category",
            "Priority", "Complexity", "Suggested Priority", "US Direction",
            "Owner to create US", "Jira Created", "Record Status", "Audit Timestamp"
    };

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * PUBLIC API 1:
     * Create/update File 1 (current) and File 2 (backup) under /backup directory.
     * - backup/Ideas_Backup.xlsx = previous snapshot
     * - backup/Ideas_Current.xlsx = latest snapshot
     */
    public static void writeIdeasSnapshotWithBackup(List<Idea> ideas) {
        try {
            // Ensure backup directory exists
            Files.createDirectories(BACKUP_DIR);

            Path currentPath = BACKUP_DIR.resolve(CURRENT_FILE_NAME);
            Path backupPath  = BACKUP_DIR.resolve(BACKUP_FILE_NAME);

            // If current exists, copy to backup first
            if (Files.exists(currentPath)) {
                Files.copy(currentPath, backupPath, StandardCopyOption.REPLACE_EXISTING);
            }

            // Now write new snapshot into current
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Ideas");

                createHeaderRow(workbook, sheet, HEADERS);
                fillSnapshotData(sheet, ideas);
                autosizeColumns(sheet, HEADERS.length);

                try (FileOutputStream out = new FileOutputStream(currentPath.toFile())) {
                    workbook.write(out);
                }
            }

            System.out.println("✅ Snapshot + Backup written to folder: " + BACKUP_DIR.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * PUBLIC API 2:
     * Append a single idea into the audit file (File 3) with record status.
     * Example statuses: "ACTIVE", "DELETED", "UPDATED"
     */
    public static void appendAuditRecord(Idea idea, String recordStatus) {
        try {
            Files.createDirectories(BACKUP_DIR);
            Path auditPath = BACKUP_DIR.resolve(AUDIT_FILE_NAME);

            Workbook workbook;
            Sheet sheet;

            File auditFile = auditPath.toFile();
            if (auditFile.exists()) {
                try (FileInputStream fis = new FileInputStream(auditFile)) {
                    workbook = new XSSFWorkbook(fis);
                }
                sheet = workbook.getSheetAt(0);
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("IdeasHistory");
                createHeaderRow(workbook, sheet, AUDIT_HEADERS);
            }

            int lastRowNum = sheet.getLastRowNum();
            Row row = sheet.createRow(lastRowNum + 1);

            int col = 0;
            row.createCell(col++).setCellValue(idea.getId() != null ? idea.getId() : 0);
            row.createCell(col++).setCellValue(idea.getStartTime() != null ? idea.getStartTime().format(DATE_FMT) : "");
            row.createCell(col++).setCellValue(idea.getCompletionTime() != null ? idea.getCompletionTime().format(DATE_FMT) : "");
            row.createCell(col++).setCellValue(idea.getEmail() != null ? idea.getEmail() : "");
            row.createCell(col++).setCellValue(idea.getName() != null ? idea.getName() : "");
            row.createCell(col++).setCellValue(idea.getToilItem() != null ? idea.getToilItem() : "");
            row.createCell(col++).setCellValue(idea.getDescription() != null ? idea.getDescription() : "");
            row.createCell(col++).setCellValue(idea.getImpactedComponents() != null ? idea.getImpactedComponents() : "");
            row.createCell(col++).setCellValue(idea.getPainPoints() != null ? idea.getPainPoints() : "");
            row.createCell(col++).setCellValue(idea.getFrequency() != null ? idea.getFrequency() : "");
            row.createCell(col++).setCellValue(idea.getManualEffort() != null ? idea.getManualEffort() : "");
            row.createCell(col++).setCellValue(idea.getAutomatable() != null && idea.getAutomatable() ? "Yes" : "No");
            row.createCell(col++).setCellValue(idea.getImpact() != null ? idea.getImpact() : "");
            row.createCell(col++).setCellValue(idea.getSolution() != null ? idea.getSolution() : "");
            row.createCell(col++).setCellValue(idea.getStatus() != null ? idea.getStatus() : "");
            row.createCell(col++).setCellValue(idea.getComments() != null ? idea.getComments() : "");
            row.createCell(col++).setCellValue(idea.getTypeOfAsk() != null ? idea.getTypeOfAsk() : "");
            row.createCell(col++).setCellValue(idea.getCategory() != null ? idea.getCategory() : "");
            row.createCell(col++).setCellValue(idea.getPriority() != null ? idea.getPriority() : "");
            row.createCell(col++).setCellValue(idea.getComplexity() != null ? idea.getComplexity() : "");
            row.createCell(col++).setCellValue(idea.getSuggestedPriority() != null ? idea.getSuggestedPriority() : 0);
            row.createCell(col++).setCellValue(idea.getUsDirection() != null ? idea.getUsDirection() : "");
            row.createCell(col++).setCellValue(idea.getOwnerToCreateUS() != null ? idea.getOwnerToCreateUS() : "");
            row.createCell(col++).setCellValue(idea.getJiraCreated() != null && idea.getJiraCreated() ? "Yes" : "");
            row.createCell(col++).setCellValue(recordStatus != null ? recordStatus : "UNKNOWN");
            row.createCell(col).setCellValue(LocalDateTime.now().format(DATE_FMT));

            autosizeColumns(sheet, AUDIT_HEADERS.length);

            try (FileOutputStream fos = new FileOutputStream(auditPath.toFile())) {
                workbook.write(fos);
            }
            workbook.close();

            System.out.println("✅ Audit updated at: " + auditPath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ===== Helper methods =====

    private static void createHeaderRow(Workbook workbook, Sheet sheet, String[] headers) {
        Row header = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }
    }

    private static void fillSnapshotData(Sheet sheet, List<Idea> ideas) {
        int rowIdx = 1;
        for (Idea item : ideas) {
            Row row = sheet.createRow(rowIdx++);

            row.createCell(0).setCellValue(item.getStartTime() != null ? item.getStartTime().format(DATE_FMT) : "");
            row.createCell(1).setCellValue(item.getCompletionTime() != null ? item.getCompletionTime().format(DATE_FMT) : "");
            row.createCell(2).setCellValue(item.getEmail() != null ? item.getEmail() : "");
            row.createCell(3).setCellValue(item.getName() != null ? item.getName() : "");
            row.createCell(4).setCellValue(item.getToilItem() != null ? item.getToilItem() : "");
            row.createCell(5).setCellValue(item.getDescription() != null ? item.getDescription() : "");
            row.createCell(6).setCellValue(item.getImpactedComponents() != null ? item.getImpactedComponents() : "");
            row.createCell(7).setCellValue(item.getPainPoints() != null ? item.getPainPoints() : "");
            row.createCell(8).setCellValue(item.getFrequency() != null ? item.getFrequency() : "");
            row.createCell(9).setCellValue(item.getManualEffort() != null ? item.getManualEffort() : "");
            row.createCell(10).setCellValue(item.getAutomatable() != null && item.getAutomatable() ? "Yes" : "No");
            row.createCell(11).setCellValue(item.getImpact() != null ? item.getImpact() : "");
            row.createCell(12).setCellValue(item.getSolution() != null ? item.getSolution() : "");
            row.createCell(13).setCellValue(item.getStatus() != null ? item.getStatus() : "");
            row.createCell(14).setCellValue(item.getComments() != null ? item.getComments() : "");
            row.createCell(15).setCellValue(item.getTypeOfAsk() != null ? item.getTypeOfAsk() : "");
            row.createCell(16).setCellValue(item.getCategory() != null ? item.getCategory() : "");
            row.createCell(17).setCellValue(item.getPriority() != null ? item.getPriority() : "");
            row.createCell(18).setCellValue(item.getComplexity() != null ? item.getComplexity() : "");
            row.createCell(19).setCellValue(item.getSuggestedPriority() != null ? item.getSuggestedPriority() : 0);
            row.createCell(20).setCellValue(item.getUsDirection() != null ? item.getUsDirection() : "");
            row.createCell(21).setCellValue(item.getOwnerToCreateUS() != null ? item.getOwnerToCreateUS() : "");
            row.createCell(22).setCellValue(item.getJiraCreated() != null && item.getJiraCreated() ? "Yes" : "");
        }
    }

    private static void autosizeColumns(Sheet sheet, int columnCount) {
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
