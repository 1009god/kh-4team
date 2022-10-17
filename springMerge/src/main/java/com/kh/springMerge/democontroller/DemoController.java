package com.kh.springMerge.democontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
	@GetMapping("/kimseunghee")
	@ResponseBody
	public String kimseunghee() {
		return "kimseunghee";
		//승희 충돌 테스트
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

	@GetMapping("/minjeong3")
	@ResponseBody
	public String minjoeng3() {
		return "minjeong3";
	}
	


	@GetMapping("/taerim")
	@ResponseBody
	public String taerim1() {
		return "minjeong3";
		}

	@GetMapping("/minjeong4")
	@ResponseBody
	public String minjeong4() {
		return "minjeong4";
	}


	@RequestMapping("/jiahn31")
	@ResponseBody
	public String jiahn31() {
		return "jiahn31";

	}
	@GetMapping("/kimjongyeon")
	public String kimjongyeon() {
		return "add";
	}

	
	@RequestMapping("/taerim")
	@ResponseBody
	public String taerim2() {
		return "jiahn31";
	}
	//추가 주석 테스트 3//

}
