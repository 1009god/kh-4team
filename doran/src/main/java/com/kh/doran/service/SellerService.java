package com.kh.doran.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.doran.entity.SellerDto;

public interface SellerService {
	int write(SellerDto sellerDto, List<MultipartFile> files) throws IllegalStateException, IOException;
	boolean remove(int sellerMemNo);
}
