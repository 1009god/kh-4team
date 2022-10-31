package com.kh.doran.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.doran.entity.OptionsDto;
import com.kh.doran.entity.PjDto;
import com.kh.doran.repository.OptionsDao;
import com.kh.doran.repository.PjDao;
import com.kh.doran.vo.PjFileVO;
@Service
public class PjserviceImpl implements Pjservice{
	
	@Autowired
	private PjDao pjDao;
	@Autowired
	private OptionsDao optionsDao;
	


	@Override
	public int insert(PjDto pjDto,List<MultipartFile> files, OptionsDto optionsDto) {
		int pjSeqNo = pjDao.sequence();
		
		pjDto.setPjNo(pjSeqNo);
		pjDao.insert(pjDto);
		
		optionsDto.setOptionsPjNo(pjSeqNo);
		optionsDao.insert(optionsDto);
		
		return pjSeqNo;
	}



	}
