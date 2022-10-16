package com.kh.springMerge.democontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
	@GetMapping("/kimseunghee")
	@ResponseBody
	public String kimseunghee() {
		return "kimseunghee";
	}
	
	@GetMapping("/jeongminjeong")
	@ResponseBody
	public String jeongminjeong() {
		return "jeongminjeong";
	}
	
	@GetMapping("/jeongminjeong")
	@ResponseBody
	public String jeongminjeong2() {
		return "jeongminjeong";
	}
	
	@GetMapping("/choijiahn")
	@ResponseBody
	public String choijiahn3() {
		return "jiahn";
	}
	
	@GetMapping("/minjeong")
	@ResponseBody
	public String minjeong() {
		return "minjeong";
	}
	
	@GetMapping("/minjeong2")
	@ResponseBody
	public String minjoeng2() {
		return "minjeong2";
	}

	
}
