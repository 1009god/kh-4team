package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.OrdersDto;
import com.kh.doran.vo.CreatedDetailVO;
import com.kh.doran.vo.OrdersMemNoSearchVO;
import com.kh.doran.vo.SupportDetailVO;
import com.kh.doran.vo.SupportListVO;

public interface OrdersDao {
	

	void insert(OrdersDto ordersDto);
	List<OrdersMemNoSearchVO> memNoSearch(int ordersMemNo);
	
	List<SupportListVO> selectSupportList(int ordersMemNo); //support한 내역 list
	SupportListVO selectSupportDetail(int ordersNo); //support한 내역 detail - 프로젝트 정보
	SupportDetailVO selectSupportDetail2(int ordersNo); // support한 내역 detatil - order, 옵션, 배송지 정보
	
	//구매취소
	boolean orderCancel(int ordersNo);
	
	//내가 만든 프로젝트들 중에서 한 개의 프로젝트를 골라서 주문 들어온 거 모아보기
	List<CreatedDetailVO> selectCreatedDetail(int optionsPjNo);
	

}
