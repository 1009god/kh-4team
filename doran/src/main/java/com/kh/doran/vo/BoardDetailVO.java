package com.kh.doran.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class BoardDetailVO {//세부에서 닉네임 보이게
	
	private int boardPostNo, boardMemNo, boardViewCnt, boardReplyCnt;
	private String boardTitle, boardContent;
	private Date boardWriteTime;
	private String memNick;

}
