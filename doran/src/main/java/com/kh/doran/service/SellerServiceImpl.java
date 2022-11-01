package com.kh.doran.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.doran.entity.FilesDto;
import com.kh.doran.entity.SellerDto;
import com.kh.doran.repository.FilesDao;
import com.kh.doran.repository.SellerDao;

@Service
public class SellerServiceImpl implements SellerService{
	
	@Autowired
	private SellerDao sellerDao;
	
	@Autowired
	private FilesDao filesDao;
	
	private final File directory = new File(System.getProperty("user.home"), "doranupload");

//	@Override
//	public int write(SellerDto sellerDto, List<MultipartFile> files) throws IllegalStateException, IOException {
//		
//		for(MultipartFile file : files) {
//			if(!file.isEmpty()) {
//				System.out.println("첨부파일 발견");
//				
//		
//				
//				int filesNo = filesDao.sequence();
//				filesDao.insert(FilesDto.builder()
//						.filesNo(filesNo)
//						.filesUploadname(file.getOriginalFilename())
//						.filesType(file.getContentType())
//						.filesSize(file.getSize())
//						.build());
//				//파일저장
//				File target = new File(directory,String.valueOf(filesNo));
//				System.out.println(target.getAbsolutePath());
//				file.transferTo(target);
//				
//				//연결 테이블에 연결정보 저장(셀러회원번호, 첨부파일번호)
//				filesDao.connectSellerFiles(sellerMemNo, filesNo);	
//				
//		return 0;
//	}
//
//	@Override
//	public boolean remove(int sellerMemNo) {
//		// TODO Auto-generated method stub
//		return false;
//	}
	
}
