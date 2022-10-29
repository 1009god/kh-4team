package com.kh.doran.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionsDto {
	private int optionsNo;
	private int optionsPjNo;
	private String optionsName;
	private int optionsPrice;
	private int optionsStock;
	private int optionsDeliveryPrice;
}

//OPTIONS_NO NUMBER PRIMARY KEY,
//OPTIONS_PJ_NO REFERENCES PJ(PJ_NO) ON DELETE CASCADE,
//OPTIONS_NAME VARCHAR2(2000) NOT NULL,
//OPTIONS_PRICE NUMBER NOT NULL CHECK(OPTIONS_PRICE>=0),
//OPTIONS_STOCK NUMBER NOT NULL CHECK(OPTIONS_STOCK>=0),
//OPTIONS_DELIVERY_PRICE NUMBER NOT NULL CHECK(OPTIONS_DELIVERY_PRICE IN(2500,0))