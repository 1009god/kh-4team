package com.kh.doran.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SellerQDto {
	private int sellerQNo, sellerQMemNo,sellerQPjNo;
	private String sellerQTitle, sellerQContent;
	private Date sellerQWritetime;	

}
