package com.kksg.service.impl;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.kksg.entity.CitizenPlan;
import com.kksg.repo.CitizenPlanRepo;
import com.kksg.request.SearchRequest;
import com.kksg.service.ReportService;
import com.kksg.util.EmailUtils;
import com.kksg.util.ExcelGenerator;
import com.kksg.util.PdfGenerator;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepo planRepo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils;
	
		

	@Override
	public List<String> getAllPlanNames() {
		return planRepo.findPlanNames();
	}

	@Override
	public List<String> getAllPlanStatus() {
		return planRepo.findPlanStatuses();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest searchRequest) {

		CitizenPlan cPlan = new CitizenPlan();

		if (null != searchRequest.getPlanName() && !"".equals(searchRequest.getPlanName())) {
			cPlan.setPlanName(searchRequest.getPlanName());
		}
		if (null != searchRequest.getPlanStatus() && !"".equals(searchRequest.getPlanStatus())) {
			cPlan.setPlanStatus(searchRequest.getPlanStatus());
		}

		if (null != searchRequest.getGender() && !"".equals(searchRequest.getGender())) {
			cPlan.setGender(searchRequest.getGender());
		}

		if (null != searchRequest.getPlanStartDate() && !"".equals(searchRequest.getPlanStartDate())) {
			String startDate = searchRequest.getPlanStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// Converting String date to LocalDate
			LocalDate date = LocalDate.parse(startDate, formatter);
			cPlan.setPlanStartDate(date);
		}

		if (null != searchRequest.getPlanEndDate() && !"".equals(searchRequest.getPlanEndDate())) {
			String endDate = searchRequest.getPlanEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// Converting String date to LocalDate
			LocalDate date = LocalDate.parse(endDate, formatter);
			cPlan.setPlanStartDate(date);
		}

		// BeanUtils.copyProperties(searchRequest, cPlan);
		return planRepo.findAll(Example.of(cPlan));
	}

	@Override
	public boolean generatePdf(HttpServletResponse response) throws Exception {
		
		File f = new File("plans.pdf");
		pdfGenerator.generate(response, f);
		
		//Methods to send an email with attachments
		String subject = "Generated Excel Report";
		String body = """
				<h1>Hello There!</>
				""";
		String to = "codewithkrishan@gmail.com";
				
		emailUtils.sendEmail(subject, to, body, f);
		f.delete();
		
		return true;
	}

	@Override
	public boolean generateExcel(HttpServletResponse response) throws Exception {

		
		File file = new File("plans_data.xls");
		excelGenerator.generate(response, file);
		
		String subject = "Generated Excel Report";
		String body = """
				<h1>Hello There!</>
				""";
		String to = "codewithkrishan@gmail.com";
				
		emailUtils.sendEmail(subject, to, body, file);
		
		file.delete();
		
		return true;
	}

}
