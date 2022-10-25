package com.kh.doran.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FilesDto {
	private int filesNo; //PK(시퀀스)
	private String filesUploadname;  // 사용자가 업로드한 파일명
	//private String filesSavename;  //실제로 저장된 파일명(시퀀스로 대체하여 사용) 왜?->  
	private String filesType; //파일의 유형
	private long filesSize; //파일의 크기 
	
}

//파일 관련된 정보 중 무엇을 DB에 넣을 것인가?
//- PK(시퀀스)
//- 사용자가 업로드한 파일명
//- 실제로 저장된 파일명(시퀀스로 대체하여 사용)
//- 업로드한 파일의 유형
//- 업로드한 파일의 크기

