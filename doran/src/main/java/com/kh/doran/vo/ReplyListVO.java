package com.kh.doran.vo;

import java.sql.Date;

import com.kh.doran.entity.BoardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyListVO {
	private int boardPostNo, boardMemNo, boardViewCnt, boardReplyCnt;
	private String boardTitle, boardContent;
	private Date boardWriteTime;
}
