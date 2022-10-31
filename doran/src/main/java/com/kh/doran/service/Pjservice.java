package com.kh.doran.service;

import java.io.IOException;

import com.kh.doran.vo.PjInsertVO;

public interface Pjservice {
	void insert(PjInsertVO pjInsertVO) throws IllegalStateException, IOException;
}
