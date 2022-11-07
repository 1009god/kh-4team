package com.kh.doran.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.doran.entity.FilesDto;
import com.kh.doran.entity.NoticeDto;
import com.kh.doran.repository.FilesDao;
import com.kh.doran.repository.NoticeDao;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private FilesDao filesDao;
	
	private final File directory = new File("D:/upload/kh10D");
	
	@Override
	public int write(NoticeDto noticeDto, List<MultipartFile> files) throws IllegalStateException, IOException {
		//문제점 : 등록은 되는데 몇 번인지 알 수 없다
				//해결책 : 번호를 미리 생성하고 등록하도록 메소드 변경
				int noticeNo = noticeDao.insert2(noticeDto);
				
				
				//(+ 추가) 게시글이 등록된 다음 파일이 있다면 해당 파일을 등록 및 연결
				//- 첨부파일이 없어도 리스트에는 1개의 객체가 들어있다
				for(MultipartFile file : files) {
						
						int filesNo = filesDao.sequence();
						filesDao.insert(FilesDto.builder()
								.filesNo(filesNo)
								.filesUploadname(file.getOriginalFilename())
								.filesType(file.getContentType())
								.filesSize(file.getSize())
								.build());
						//파일저장
						File target = new File(directory,String.valueOf(filesNo));
						file.transferTo(target);
						
						//+ 연결 테이블에 연결 정보를 저장 (게시글 번호, 첨부파일 번호)
						noticeDao.connectFiles(noticeNo, filesNo);
				}
		return noticeNo;
	}
	
	@Override
	public boolean remove(int noticeNo) {
		List<FilesDto> fileList = 
				filesDao.selectNoticeFileList(noticeNo);
		
		//삭제
		boolean result = noticeDao.delete(noticeNo);
		
		if(result) {
			for(FilesDto filesDto : fileList) {
				//첨부파일 (files) 테이블 삭제
				filesDao.delete(filesDto.getFilesNo());
				//실제 파일 삭제
				String filename = String.valueOf(filesDto.getFilesNo());
				File target = new File(directory, filename);
				target.delete();
			}
		}
		return result;
	}
}
