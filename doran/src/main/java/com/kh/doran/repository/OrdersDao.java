package com.kh.doran.repository;

import java.util.List;

import com.kh.doran.entity.OrdersDto;
import com.kh.doran.vo.OrdersMemNoSearchVO;

public interface OrdersDao {
	

	void insert(OrdersDto ordersDto);
	List<OrdersMemNoSearchVO> memNoSearch(int ordersMemNo);

}
