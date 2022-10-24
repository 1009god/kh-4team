package com.kh.doran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.doran.repository.AdminPjDao;

@Controller
@RequestMapping("/admin")
public class AdminPjController {

	@Autowired
	private AdminPjDao adminPjDao;
}
