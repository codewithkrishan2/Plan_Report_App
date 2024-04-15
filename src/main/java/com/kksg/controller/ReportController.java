package com.kksg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kksg.entity.CitizenPlan;
import com.kksg.request.SearchRequest;
import com.kksg.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@GetMapping("/")
	public String indexPage(Model model) {
		
		model.addAttribute("search", new SearchRequest());
		initmodel(model);
				
		return "index";
	}

	
	@PostMapping("/search")
	public String handleSearch(SearchRequest search, Model model) {
		System.out.println(search);
		
		List<CitizenPlan> list = reportService.search(search);
		model.addAttribute("allPlans",list);
		model.addAttribute("search", search); //sending pre-populated data to back to ui.
				
		initmodel(model);
		return "index";
	}
	
	private void initmodel(Model model) {
		model.addAttribute("plans", reportService.getAllPlanNames());
		model.addAttribute("statuses", reportService.getAllPlanStatus());
	}
	
	@GetMapping("/excel")
	public void exportExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		
		response.addHeader("Content-Disposition", "attachment;filename=plans.xls");
		
		reportService.generateExcel(response);
		
	}
	
	@GetMapping("/pdf")
	public void exportPdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;filename=plans.pdf");
		reportService.generatePdf(response);
		
		
	}
	
}
