package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.MemDto;
import com.kh.doran.entity.SellerDto;
import com.kh.doran.vo.MemListSearchVO;
import com.kh.doran.vo.SellerListSearchVO;

public interface AdminSellerDao {

	List<SellerDto> selectList();
	
	List<SellerDto> selectList(SellerListSearchVO vo);
	List<SellerDto> list(SellerListSearchVO vo);
	List<SellerDto> search(SellerListSearchVO vo);
	
	int count(SellerListSearchVO vo);
	int searchCount(SellerListSearchVO vo);
	int listCount(SellerListSearchVO vo);
	
	SellerDto selectOne(int sellerNo);
	boolean delete(int sellerNo);
}
