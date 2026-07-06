package com.college.sms.export;

import com.college.sms.entity.Marks;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class MarksExcelExporter {

    private final List<Marks> marksList;

    public MarksExcelExporter(List<Marks> marksList) {
        this.marksList = marksList;
    }

    public void export(HttpServletResponse response) throws IOException {

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Marks Report");

        // ================= Header =================

        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Student Name");
        header.createCell(2).setCellValue("Subject");
        header.createCell(3).setCellValue("Internal");
        header.createCell(4).setCellValue("External");
        header.createCell(5).setCellValue("Total");
        header.createCell(6).setCellValue("Grade");
        header.createCell(7).setCellValue("Result");

        // ================= Data =================

        int rowCount = 1;

        for (Marks marks : marksList) {

            Row row = sheet.createRow(rowCount++);

            row.createCell(0).setCellValue(marks.getId());

            row.createCell(1).setCellValue(
                    marks.getStudent().getFirstName() + " "
                            + marks.getStudent().getLastName());

            row.createCell(2).setCellValue(marks.getSubject());

            row.createCell(3).setCellValue(marks.getInternalMarks());

            row.createCell(4).setCellValue(marks.getExternalMarks());

            row.createCell(5).setCellValue(marks.getTotalMarks());

            row.createCell(6).setCellValue(marks.getGrade());

            row.createCell(7).setCellValue(marks.getResult());

        }

        // ================= Auto Size Columns =================

        for (int i = 0; i < 8; i++) {
            sheet.autoSizeColumn(i);
        }

        ServletOutputStream outputStream =
                response.getOutputStream();

        workbook.write(outputStream);

        workbook.close();

        outputStream.close();

    }

}