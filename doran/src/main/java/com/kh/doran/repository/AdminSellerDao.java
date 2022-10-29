package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.MemDto;
import com.kh.doran.entity.SellerDto;
import com.kh.doran.vo.AdminsellerDetailVO;
import com.kh.doran.vo.AdminsellerListVO;
import com.kh.doran.vo.BoardDetailVO;
import com.kh.doran.vo.MemListSearchVO;
import com.kh.doran.vo.SellerListSearchVO;

public interface AdminSellerDao {

	List<SellerDto> selectList();
	
	List<AdminsellerListVO> selectList(SellerListSearchVO vo);
	List<AdminsellerListVO> list(SellerListSearchVO vo);
	List<AdminsellerListVO> search(SellerListSearchVO vo);
	
	int count(SellerListSearchVO vo);
	int searchCount(SellerListSearchVO vo);
	int listCount(SellerListSearchVO vo);
	
	AdminsellerDetailVO selectOne1(int sellerMemNo);
	
	SellerDto selectOne(int sellerMemNo);
	boolean delete(int sellerMemNo);
	
	boolean agree(int sellerMemNo);
	boolean revoke(int sellerMemNo);
}
