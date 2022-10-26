package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.ReplyDto;
import com.kh.doran.vo.ReplyListVO;

public interface ReplyDao {
	void insert(ReplyDto replyDto);
	List<ReplyListVO> selectList(int replyBoardPostNo);
	ReplyDto selectOne(int replyNo);
	boolean update(ReplyDto replyDto);
	boolean delete(int replyNo);

}
