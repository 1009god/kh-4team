package com.kh.doran.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FaqDto {

	private int faqNo;
	private int faqAdminNo;
	private String faqType;
	private String faqTitle;
	private String faqContent;
	
}
