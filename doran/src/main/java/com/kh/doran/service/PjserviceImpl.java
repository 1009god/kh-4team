package com.kh.doran.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.doran.entity.FilesDto;
import com.kh.doran.entity.OptionsDto;
import com.kh.doran.entity.PjDto;
import com.kh.doran.repository.FilesDao;
import com.kh.doran.repository.OptionsDao;
import com.kh.doran.repository.PjDao;
import com.kh.doran.vo.PjInsertVO;

@Service
public class PjserviceImpl implements Pjservice {

	@Autowired
	private PjDao pjDao; 

	@Autowired
	private OptionsDao optionsDao;
	
	@Autowired
	private FilesDao filesDao;
	
	private File root = new File(System.getProperty("user.home")+"/doranupload");
	
	@PostConstruct
	public void init() {
		System.out.println(root.getAbsolutePath());
		root.mkdirs();
	}
	
	@Override
	public void insert(PjInsertVO pjInsertVO) throws IllegalStateException, IOException {
		//프로젝트 등록
		int pjNo = pjDao.sequence();
		pjDao.insert(PjDto.builder()
					.pjNo(pjNo)
					.pjSellerMemNo(pjInsertVO.getSellerMemNo())
					.pjCategory(pjInsertVO.getPjCategory())
					.pjName(pjInsertVO.getPjName())
					.pjSummary(pjInsertVO.getPjSummary())
					.pjTargetMoney(pjInsertVO.getPjTargetMoney())
					.pjFundingStartDate(pjInsertVO.getPjFundingStartDate())
					.pjFundingEndDate(pjInsertVO.getPjFundingEndDate())
					.pjEndDate(pjInsertVO.getPjEndDate())
				.build());
		
		//옵션 등록
		for(OptionsDto optionsDto : pjInsertVO.getOptions()) {
			optionsDto.setOptionsPjNo(pjNo);
			optionsDao.insert(optionsDto);
		}
		
		//첨부파일 등록
		//- 배너이미지(1개)
		MultipartFile bannerFile = pjInsertVO.getPjFileBanner();
		int bannerFileNo = filesDao.sequence();
		filesDao.insert(FilesDto.builder()
					.filesNo(bannerFileNo)
					.filesUploadname(bannerFile.getOriginalFilename())
					.filesSize(bannerFile.getSize())
					.filesType(bannerFile.getContentType())
				.build());
		filesDao.connectPjFiles(pjNo, bannerFileNo, "배너");
		File bannerTarget = new File(root, String.valueOf(bannerFileNo));
		bannerFile.transferTo(bannerTarget);
		
		//- 소개이미지(1개)
		MultipartFile introFile = pjInsertVO.getPjFileIntro();
		int introFileNo = filesDao.sequence();
		filesDao.insert(FilesDto.builder()
					.filesNo(introFileNo)
					.filesUploadname(introFile.getOriginalFilename())
					.filesSize(introFile.getSize())
					.filesType(introFile.getContentType())
				.build());
		filesDao.connectPjFiles(pjNo, introFileNo, "소개");
		File introTarget = new File(root, String.valueOf(introFileNo));
		introFile.transferTo(introTarget);
		
		//- 대표이미지(n개)
		List<MultipartFile> mainFiles = pjInsertVO.getPjFileMains();
		for(MultipartFile mainFile : mainFiles) {
			int mainFileNo = filesDao.sequence();
			filesDao.insert(FilesDto.builder()
					.filesNo(mainFileNo)
					.filesUploadname(mainFile.getOriginalFilename())
					.filesSize(mainFile.getSize())
					.filesType(mainFile.getContentType())
				.build());
			filesDao.connectPjFiles(pjNo, mainFileNo, "대표");
			File mainTarget = new File(root, String.valueOf(mainFileNo));
			mainFile.transferTo(mainTarget);
		}
		
	}

}
