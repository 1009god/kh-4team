package com.kh.doran.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AdminDto {

	private int adminNo;
	private String adminEmail,adminPw,adminNick;
}
