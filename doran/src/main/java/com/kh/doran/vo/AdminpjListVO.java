package com.kh.doran.vo;

import java.sql.Date;

import com.kh.doran.entity.PjDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminpjListVO {

	private int pjNo;
	private int pjSellerMemNo;
	private String pjCategory;
	private String pjName;
	private String pjSummary;
	private int pjTargetMoney;
	private String memNick;
}
