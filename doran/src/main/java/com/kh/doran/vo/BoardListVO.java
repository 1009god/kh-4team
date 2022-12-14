package com.kh.doran.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class BoardListVO {//목록에서 닉네임 보이게
	
	private int boardPostNo, boardMemNo, boardViewCnt, boardReplyCnt;
	private String boardTitle;
	private Date boardWriteTime;
	private int replyCount;
	private String memNick;

}
