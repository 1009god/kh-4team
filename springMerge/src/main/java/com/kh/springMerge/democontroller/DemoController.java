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

	
}
