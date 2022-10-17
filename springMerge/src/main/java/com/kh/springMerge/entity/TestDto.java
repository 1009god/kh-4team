package com.kh.springMerge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  @NoArgsConstructor @AllArgsConstructor @Builder
public class TestDto {
	
	//lombok 테스트
	private int test;
	private String test2;
	private String name;
}
