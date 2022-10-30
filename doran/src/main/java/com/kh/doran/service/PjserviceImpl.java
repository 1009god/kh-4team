package com.kh.doran.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.doran.entity.OptionsDto;
import com.kh.doran.entity.PjDto;
import com.kh.doran.repository.OptionsDao;
import com.kh.doran.repository.PjDao;
@Service
public class PjserviceImpl implements Pjservice{
	
	@Autowired
	private PjDao pjDao;
	@Autowired
	private OptionsDao optionsDao;
	


	@Override
	public int insert(PjDto pjDto, OptionsDto optionsDto) {
		int pjSeqNo = pjDao.sequence();
		
		pjDto.setPjNo(pjSeqNo);
		pjDao.insert(pjDto);
		
		optionsDto.setOptionsPjNo(pjSeqNo);
		optionsDao.insert(optionsDto);
		
		return pjSeqNo;
	}}
