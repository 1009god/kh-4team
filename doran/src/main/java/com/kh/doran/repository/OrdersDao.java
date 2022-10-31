package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.OrdersDto;
import com.kh.doran.vo.OrdersMemNoSearchVO;
import com.kh.doran.vo.SupportDetailVO;
import com.kh.doran.vo.SupportListVO;

public interface OrdersDao {
	

	void insert(OrdersDto ordersDto);
	List<OrdersMemNoSearchVO> memNoSearch(int ordersMemNo);
	
	List<SupportListVO> selectSupportList(int ordersMemNo); //support한 내역 list
	SupportListVO selectSupportDetail(int ordersNo); //support한 내역 detail - 프로젝트 정보
	SupportDetailVO selectSupportDetail2(int ordersNo); // support한 내역 detatil - order, 옵션, 배송지 정보
	
	

}
