package com.kksg.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

	private String gender;
	private String planName;
	private String planStatus;
	private String planStartDate;
	private String planEndDate;
}
