package com.kksg.service;

import java.util.List;

import com.kksg.entity.CitizenPlan;
import com.kksg.request.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {

	List<String> getAllPlanNames();
	
	List<String> getAllPlanStatus();
	
	List<CitizenPlan> search(SearchRequest searchRequest);
	
	boolean generatePdf(HttpServletResponse response) throws Exception ;
	
	boolean generateExcel(HttpServletResponse response) throws Exception;
}
