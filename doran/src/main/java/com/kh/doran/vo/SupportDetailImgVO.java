package com.kh.doran.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SupportDetailImgVO {
	
	private int pjFilePjNo, pjFileNo, ordersNo;
	private String pjFileClassify;
}
