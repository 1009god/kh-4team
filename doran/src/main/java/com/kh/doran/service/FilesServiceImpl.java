package com.kh.doran.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kh.doran.entity.FilesDto;
import com.kh.doran.error.TargetNotFoundException;
import com.kh.doran.repository.FilesDao;

@Service
public class FilesServiceImpl implements FilesService{
	
	@Autowired
	private FilesDao filesDao;
	
	private final File dir = new File("D:/doranupload");
	
	@Override
	public ResponseEntity<ByteArrayResource> load(int filesNo) throws IOException {
		//파일탐색(db)
		FilesDto dto = filesDao.selectOne(filesNo);
		if(dto==null) {//파일이 없다면
			throw new TargetNotFoundException("존재하지 않는 파일");
		}
		//파일 불러오기
		File target = new File(dir, String.valueOf(filesNo));
		byte[] data =FileUtils.readFileToByteArray(target);
		ByteArrayResource resource = new ByteArrayResource(data);
		///응답 객체를 만들어 데이터를 전송
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_ENCODING, 
						StandardCharsets.UTF_8.name())
						.contentLength(dto.getFilesSize())
						.header(HttpHeaders.CONTENT_TYPE , dto.getFilesType())
						.header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
						.filename(dto.getFilesUploadname(), StandardCharsets.UTF_8).build().toString())
						.body(resource);
	}
	
}
