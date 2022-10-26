package com.kh.doran.service;



import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;

public interface FilesService {
	ResponseEntity<ByteArrayResource> load(int filesNo) throws IOException;
}
