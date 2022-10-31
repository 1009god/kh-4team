package com.kh.doran.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PjFileDto {

	private int pjFilePjNo;
	private int pjFileNo;
	private String pjFileClassify;
}
