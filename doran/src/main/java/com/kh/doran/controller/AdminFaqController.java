package com.kh.doran.controller;

import java.util.List;

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
import com.kh.doran.repository.FaqDao;

@Controller
@RequestMapping("/admin")
public class AdminFaqController {

	@Autowired
	private FaqDao faqDao;
	


	@GetMapping("/faqlist")
	public String list(Model model,HttpSession session) {		
		List<FaqDto> list =faqDao.selectList();
		model.addAttribute("list", faqDao.selectList());		
		if(session.getAttribute("AdminNo")!=null) {
			return "/admin/faqlist";			
		}
		else {
			return "/admin/login";
		}
	}
	@GetMapping("/faqdetail")
	public String detail(Model model, @RequestParam int faqNo,HttpSession session) {
		FaqDto faqDto = faqDao.selectOne(faqNo);
		model.addAttribute("faqDto", faqDto);
		if(session.getAttribute("AdminNo")!=null) {
			return "admin/faqdetail";			
		}
		else {
			return "admin/login";
		}
	}


	@GetMapping("/faqwrite")
	public String insert() {
		return "admin/faqwrite";
	}

	@PostMapping("/faqwrite")
	public String insert(@ModelAttribute FaqDto faqDto,HttpSession session) {
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
