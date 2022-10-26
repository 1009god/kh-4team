package com.kh.doran.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.doran.entity.SellerDto;
import com.kh.doran.repository.SellerDao;

@Service
public class SellerServiceImpl implements SellerService{
	
	@Autowired
	private SellerDao sellerDao;
	
	private final File directory = new File("D:/doranupload");

	@Override
	public int write(SellerDto sellerDto, List<MultipartFile> files) throws IllegalStateException, IOException {
//		등록될 
		//int filesNo//
		return 0;
	}

	@Override
	public boolean remove(int sellerMemNo) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
