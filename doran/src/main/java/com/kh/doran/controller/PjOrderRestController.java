package com.kh.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.doran.entity.AddressDto;
import com.kh.doran.repository.AddressDao;

@CrossOrigin
@RestController
@RequestMapping("/rest/pj/order")
public class PjOrderRestController {
	
	
	@Autowired
	private AddressDao addressDao;
	
	@RequestMapping("/address")
	public String address(@ModelAttribute AddressDto addressDto) {
		addressDao.insert(addressDto);
		return null;
		
		
	}

}
