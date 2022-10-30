package com.kh.doran.service;

import com.kh.doran.entity.OptionsDto;
import com.kh.doran.entity.PjDto;

public interface Pjservice {
	int insert(PjDto pjDto, OptionsDto optionsDto);
}
