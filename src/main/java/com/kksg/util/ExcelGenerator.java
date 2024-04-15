package com.kksg.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kksg.entity.CitizenPlan;
import com.kksg.repo.CitizenPlanRepo;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class ExcelGenerator {

	@Autowired
	private CitizenPlanRepo planRepo;
	
	public void generate(HttpServletResponse response, File file) throws Exception {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("plans-data");

		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Plan Name");
		headerRow.createCell(4).setCellValue("Plan Status");
		headerRow.createCell(5).setCellValue("Plan Start Date");
		headerRow.createCell(6).setCellValue("Plan End Date");
		headerRow.createCell(7).setCellValue("Benefit Amount");

		List<CitizenPlan> plans = planRepo.findAll();

		int dataRowIndex = 1;

		for (CitizenPlan plan : plans) {
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getGender());
			dataRow.createCell(3).setCellValue(plan.getPlanName());
			dataRow.createCell(4).setCellValue(plan.getPlanStatus());

			dataRow.createCell(5).setCellValue(plan.getPlanStartDate() != null ? plan.getPlanStartDate() + "" : "NA");
			dataRow.createCell(6).setCellValue(plan.getPlanEndDate() != null ? plan.getPlanEndDate() + "" : "NA");

			if (plan.getBenefitAmount() != null) {
				dataRow.createCell(7).setCellValue(plan.getBenefitAmount());
			} else {
				dataRow.createCell(7).setCellValue("NA");
			}

			dataRowIndex++;
		}
		
		//for creating the file locally
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);
		fileOutputStream.close();

		//for sending the file into the browser
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
	}
}
