package com.kh.springMerge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	

	@GetMapping("/hwangtaehyeon")
	@ResponseBody
	public String hwangtaehyeon() {
		return "hwangtaehyeon";
	}

	
		
		
	
		

}
