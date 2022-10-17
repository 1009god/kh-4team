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
		//승희 충돌 테스트
	}

}
