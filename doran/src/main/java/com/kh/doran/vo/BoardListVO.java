package com.kh.doran.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class BoardListVO {
	
	private int boardPostNo, boardMemNo, boardViewCnt, boardReplyCnt;
	private String boardTitle;
	private Date boardWriteTime;
	private int replyCount;
	private String memNick;

}
