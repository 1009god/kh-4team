package com.kh.doran.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PjDto {

	private int pjNo;
	private int pjSellerMemNo;
	private String pjCategory;
	private String pjName;
	private String pjSummary;
	private int pjTargetMoney;
	private Date pjFundingStartDate;
	private Date pjFundingEndDate;
	private Date pjEndDate;
	private int pjLikesNumber;
	
	
}
