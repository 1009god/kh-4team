package com.kh.doran.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
	private int boardPostNo, boardMemNo, boardViewCnt, boardReplyCnt;
	private String boardTitle, boardContent;
	private Date boardWriteTime;
}
