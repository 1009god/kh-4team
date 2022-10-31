package com.kh.doran.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CreatedDetailVO {

	private int ordersNo;
	private int ordersMemNo;
	private int ordersOptionsNo;
	private Date ordersDate;
	private Date ordersCancelDate;
	private String ordersMessage;
	private Date ordersPayDate;
	private int ordersDeliveryPay;
	private int ordersAddressNo;
	private int optionsNo;
	private int optionsPjNo;
	private String optionsName;
	
}
