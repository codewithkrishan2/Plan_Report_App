package com.kksg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kksg.entity.CitizenPlan;

public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer> {
	
	@Query("SELECT DISTINCT(planName) FROM CitizenPlan")
	public List<String> findPlanNames();
	
	@Query("SELECT DISTINCT(planStatus) FROM CitizenPlan")
	public List<String> findPlanStatuses();
	
	@Query("SELECT DISTINCT(gender) FROM CitizenPlan")
	public List<String> findGenders();
}
