package com.kh.doran.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SupportDetailVO {
	
	private int ordersNo;
	private Date ordersDate, pjFundingEndDate;
	private String optionsName;
	private int optionsPrice, optionsDeliveryPrice;
	private int addressNo, addressMemNo;
	private String addressName, addressTel, addressPost, addressBasic, addressDetail;
}
