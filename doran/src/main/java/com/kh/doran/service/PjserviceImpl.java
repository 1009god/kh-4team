package com.kh.doran.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.kh.doran.entity.PjDto;
import com.kh.doran.repository.PjDao;

public class PjserviceImpl implements Pjservice{
	
	@Autowired
	private PjDao pjDao;
	
	@Override
	public int insert(PjDto pjDto){
		int pjSeqNo = pjDao.sequence();
		pjDto.setPjNo(pjSeqNo);
		pjDao.insert(pjDto);
		return pjSeqNo;
	}

}
