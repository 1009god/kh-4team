package com.kh.doran.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DoranQDto {

	private int DoranQNo, DorqnQmemNo, DoranQAdminNo;
	private String DoranQTitle, DoranQContent;
	private Date DoranQWriteTime;
	private String DoranQType,DoranQProcessing;
}
