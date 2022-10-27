package com.kh.doran.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ReplyListVO {
	private int replyNo, replyBoardPostNo, replyMemNo;
	private String replyContent;
	private Date replyWriteTime;
	private String memNick;
}
