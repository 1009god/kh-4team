package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.ReplyDto;

public interface ReplyDao {
	void insert(ReplyDto replyDto);
	List<ReplyDto> selectList(int replyBoardPostNo);
	boolean update(ReplyDto replyDto);
	boolean delete(int replyNo);

}
