package com.kh.doran.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SellerFileVO {
	private int sellerMemNo, sfFileNo, filesSize;
	private String filesUploadName, filesType;
	
	
}
