package com.TPB.team_portal_backend.util;

import com.TPB.team_portal_backend.model.Idea;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExcelExporter {

    private static final String[] HEADERS = {
        "Start time", "Completion time", "Email", "Name", "Toil/Improvement Item",
        "Description", "Impacted Component(s)", "Pain Points/Challenges", "Frequency",
        "Manual Effort (Time Spent)", "Automatable", "Impact(if automated)",
        "Solution", "Status", "Comments", "Type of ask", "Category",
        "Priority", "Complexity", "Suggested Priority", "US Direction",
        "Owner to create US", "Jira Created"
    };

    public static void writeIdeasToExcel(List<Idea> ideas, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Ideas");
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            // Header Row
            Row header = sheet.createRow(0);
            for (int i = 0; i < HEADERS.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(HEADERS[i]);
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                font.setColor(IndexedColors.WHITE.getIndex());
                style.setFont(font);
                style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                cell.setCellStyle(style);
            }

            // Data Rows
            int rowIdx = 1;
            for (Idea item : ideas) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(item.getStartTime() != null ? item.getStartTime().format(fmt) : "");
                row.createCell(1).setCellValue(item.getCompletionTime() != null ? item.getCompletionTime().format(fmt) : "");
                row.createCell(2).setCellValue(item.getEmail() != null ? item.getEmail() : "");
                row.createCell(3).setCellValue(item.getName() != null ? item.getName() : "");
                row.createCell(4).setCellValue(item.getToilItem() != null ? item.getToilItem() : "");
                row.createCell(5).setCellValue(item.getDescription() != null ? item.getDescription() : "");
                row.createCell(6).setCellValue(item.getImpactedComponents() != null ? item.getImpactedComponents() : "");
                row.createCell(7).setCellValue(item.getPainPoints() != null ? item.getPainPoints() : "");
                row.createCell(8).setCellValue(item.getFrequency() != null ? item.getFrequency() : "");
                row.createCell(9).setCellValue(item.getManualEffort() != null ? item.getManualEffort() : "");
                row.createCell(10).setCellValue(item.getAutomatable() != null && item.getAutomatable() ? "Yes" : "No");
                row.createCell(11).setCellValue(item.getImpact() != null ? item.getImpact().name() : "");
                row.createCell(12).setCellValue(item.getSolution() != null ? item.getSolution() : "");
                row.createCell(13).setCellValue(item.getStatus() != null ? item.getStatus().name() : "");
                row.createCell(14).setCellValue(item.getComments() != null ? item.getComments() : "");
                row.createCell(15).setCellValue(item.getTypeOfAsk() != null ? item.getTypeOfAsk() : "");
                row.createCell(16).setCellValue(item.getCategory() != null ? item.getCategory().name() : "");
                row.createCell(17).setCellValue(item.getPriority() != null ? item.getPriority().name() : "");
                row.createCell(18).setCellValue(item.getComplexity() != null ? item.getComplexity().name() : "");
                row.createCell(19).setCellValue(item.getSuggestedPriority() != null ? item.getSuggestedPriority() : 0);
                row.createCell(20).setCellValue(item.getUsDirection() != null ? item.getUsDirection() : "");
                row.createCell(21).setCellValue(item.getOwnerToCreateUS() != null ? item.getOwnerToCreateUS() : "");
                row.createCell(22).setCellValue(item.getJiraCreated() != null && item.getJiraCreated() ? "Yes" : "");
            }

            // Autosize columns
            for (int i = 0; i < HEADERS.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write file
            try (FileOutputStream out = new FileOutputStream(filePath)) {
                workbook.write(out);
            }

            System.out.println("✅ Excel file updated: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
