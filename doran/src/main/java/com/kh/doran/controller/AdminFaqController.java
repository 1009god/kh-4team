package com.kh.doran.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.kh.doran.entity.FaqDto;
import com.kh.doran.error.TargetNotFoundException;
import com.kh.doran.repository.DoranQDao;
import com.kh.doran.repository.FaqDao;

@Controller
@RequestMapping("/admin")
public class AdminFaqController {

	@Autowired
	private FaqDao faqDao;
	


	@GetMapping("/faqlist")
	public String list(Model model, @RequestParam(required = false) String type,
			@RequestParam(required = false) String keyword) {
		boolean isSearch = type != null && keyword != null;
		if (isSearch) {// 검색
			model.addAttribute("list", faqDao.selectList(type, keyword));
		} else {// 목록
			model.addAttribute("list", faqDao.selectList());
		}
		return "admin/faqlist";
	}

	@GetMapping("/faqdetail")
	public String detail(Model model, @RequestParam int faqNo) {
		FaqDto faqDto = faqDao.selectOne(faqNo);
		model.addAttribute("faqDto", faqDto);

		return "admin/faqdetail";

	}

	@GetMapping("/faqwrite")
	public String insert() {
		return "admin/faqwrite";
	}

	@PostMapping("/faqwrite")
	public String insert(@ModelAttribute FaqDto faqDto) {
		faqDao.insert(faqDto);
		return "redirect:write_success";
	}

	@GetMapping("/write_success")
	public String insertSuccess() {
		return "admin/faqwriteSuccess";
	}

	@GetMapping("/faqdelete")
	public String faqdelete(@RequestParam int faqNo) {
		boolean result = faqDao.delete(faqNo);
		if (result) {
			return "redirect:faqlist";
		} else {
			return "admin/editFail";
		}
	}

	@GetMapping("/faqedit")
	public String edit(Model model, @RequestParam int faqNo) {
		FaqDto faqDto =faqDao.selectOne(faqNo);
		if(faqDto == null) {
			throw new TargetNotFoundException();
		}
		model.addAttribute("faqDto", faqDao.selectOne(faqNo));
		return "admin/faqedit";
	}

	@PostMapping("/faqedit")
	public String edit(@ModelAttribute FaqDto faqDto, RedirectAttributes attr) {
		boolean result = faqDao.update(faqDto);
		if (result) {
			attr.addAttribute("faqNo", faqDto.getFaqNo());
			return "redirect:faqdetail";
		} else {
			throw new TargetNotFoundException();
		}
	}
	



}
