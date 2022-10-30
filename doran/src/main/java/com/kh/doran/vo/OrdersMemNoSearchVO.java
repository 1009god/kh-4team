package com.kh.doran.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersMemNoSearchVO {

	private int pjNo;
	private String pjName;
	private int optionsNo;
	private String optionsName;
	private int optionsPrice;
	private int optionsStock;
	private int optionsDeliveryPrice;
	private int ordersNo;
	private int ordersMemNo;
	
}
