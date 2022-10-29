package com.kh.doran.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class PjVO {
	
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
	private int nvl;
	private double achievementRate;

}
