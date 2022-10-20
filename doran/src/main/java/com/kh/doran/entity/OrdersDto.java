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
public class OrdersDto {
	
	private int ordersNo;
	private int ordersMemNo;
	private int ordersOptionsNo;
	private Date ordersDate;
	private Date ordersCancelDate;
	private String ordersMessage;
	private Date ordersPayDate;
	private int ordersDeliveryPay;
	private int ordersAddressNo;

}
