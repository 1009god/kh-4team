package com.kh.doran.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {

	private int addressNo;
	private int addressMemNo;
	private String addressName;
	private String addressTel;
	private String addressPost;
	private String addressBasic;
	private String addressDetail;
	
}
