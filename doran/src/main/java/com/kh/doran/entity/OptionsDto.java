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
