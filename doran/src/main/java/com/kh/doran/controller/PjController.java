package com.kh.doran.controller;

import java.io.File;
import java.io.IOException;
import java.security.KeyStore.Entry.Attribute;
import java.util.List;
import java.util.jar.Attributes;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.doran.entity.LikesDto;
import com.kh.doran.entity.OptionsDto;
import com.kh.doran.entity.OrdersDto;

import com.kh.doran.repository.FilesDao;

import com.kh.doran.repository.AddressDao;

import com.kh.doran.repository.LikesDao;
import com.kh.doran.repository.MemDao;

import com.kh.doran.entity.FilesDto;

import com.kh.doran.entity.AddressDto;


import com.kh.doran.entity.PjDto;
import com.kh.doran.entity.MemDto;

import com.kh.doran.repository.LikesDao;
import com.kh.doran.repository.OptionsDao;
import com.kh.doran.repository.OrdersDao;
import com.kh.doran.repository.PjDao;
import com.kh.doran.vo.OrdersCalVO;
import com.kh.doran.vo.PjListSearchVO;
import com.kh.doran.vo.OrderCountVO;

@Controller
@RequestMapping("/pj")
public class PjController {
	
	
	
	@Autowired
	private PjDao pjDao;
	
	@Autowired
	private OptionsDao optionsDao;
	
	@Autowired
	private LikesDao likesDao;
	
	@Autowired
	private MemDao memDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private FilesDao filesDao;
	
	@GetMapping("/insert")
	public String insert() {
		return "pj/insert";
	}
	@PostMapping("/insert")//프로젝트 입력 받은거 저장             옵션정보 저장
	public String insert(@ModelAttribute PjDto pjDto, @ModelAttribute OptionsDto optionsDto,
			
			//파일 정보
//			@RequestParam List<MultipartFile> files , 
			Attributes attr,
			//세션 관련된거 쓰겠다는거
			HttpSession session
			)throws IllegalStateException, IOException  {
		
		//등록될 프로젝트의 번호를 미리 생성함
		int pjSeqNo= pjDao.sequence();
		//생성된 번호를 프로젝트 번호로 저장함
		pjDto.setPjNo(pjSeqNo); 
		
		//세션에 있는 셀러 번호를 가져온다음 pjsellerNo에 집어넣음
		int pjsellerNo = (int)session.getAttribute("sellerNo");
		//세션에 있는 셀러번호를 PjSellerMemNo에 저장시킨다
		pjDto.setPjSellerMemNo(pjsellerNo);
		
	
		//db에 프로젝트를 먼저 만든다(dao에 있는 인서트 문에 넣어서 만들어) 
		//or 이거인가 pjDao.insert(pjDto)
		pjDao.insert(PjDto.builder()
									.pjNo(pjSeqNo)
									.pjSellerMemNo(pjsellerNo)
									.pjCategory(pjDto.getPjCategory())
									.pjName(pjDto.getPjName())
									.pjSummary(pjDto.getPjSummary())
									.pjTargetMoney(pjDto.getPjTargetMoney())
									.pjFundingStartDate(pjDto.getPjFundingStartDate())
									.pjFundingEndDate(pjDto.getPjFundingEndDate())
									.pjEndDate(pjDto.getPjFundingEndDate())
									.pjLikesNumber(pjDto.getPjLikesNumber())
									.build());
		
		//그 다음엔 Dto에 미리만든 프로젝트 시퀀스 번호를 OptionsPjNo에 저장해서 줘
		//optionsDto.setOptionsPjNo(pjSeqNo);
		//시퀀스 번호를 dto에 넣었으니까 Dao에 있는 인서트 문을 돌려 
		optionsDao.insert(OptionsDto.builder()
				
				.optionsPjNo(pjSeqNo)
				.optionsName(optionsDto.getOptionsName())
				.optionsPrice(optionsDto.getOptionsPrice())
				.optionsStock(optionsDto.getOptionsStock())
				.optionsDeliveryPrice(optionsDto.getOptionsDeliveryPrice())
				.build());
		
		
		
		
//		System.out.println(pjDto.getPjNo());
//		System.out.println(pjDto.getPjSellerMemNo());
		
		
//		for(MultipartFile file : files) {
//			if(!file.isEmpty()) {
//				System.out.println("첨부파일 발견");
//				
//			//db등록
//			int filesNo = filesDao.sequence();
//			filesDao.insert(FilesDto.builder()
//					.filesNo(filesNo)
//					.filesUploadname(file.getOriginalFilename())
//					.filesType(file.getContentType())
//					.filesSize(file.getSize())
//					.build());
//			//파일저장
//			File dir = new File("D:/doranuplaod/pjinsertimg");
//			dir.mkdirs();
//			File target = new File(dir,String.valueOf(filesNo));
//			file.transferTo(target);
//			}
//	}
		
		return "pj/insertfinish";
	}
	@GetMapping("/insertfinish")
	public String insertfinish() {
		return "pj/insertfinish";
	}

	
	@GetMapping("/detail")
	public String detail(@RequestParam int pjNo, @ModelAttribute OrderCountVO vo, Model model, HttpSession session) {
		model.addAttribute("PjDto", pjDao.selectOne(pjNo));//프로젝트넘버로 검색해서 나온 값 model에 저장해서 넘김
		model.addAttribute("OptionsDto", optionsDao.selectList(pjNo));//pjno로 검색해서 나온 옵션들 model에 저장해서 넘김

		
		Integer loginNo=(Integer) session.getAttribute("loginNo");
		if(loginNo==null) {
			return "pj/detail";
		}
		else {
			LikesDto likesDto=new LikesDto();
			likesDto.setLikesMemNo(loginNo);
			likesDto.setLikesPjNo(pjNo);
			model.addAttribute("check",likesDao.check(likesDto));
			vo.setOptionsPjNo(pjNo);
			int loginNo2=(int) session.getAttribute("loginNo");
			vo.setOrdersMemNo(loginNo2);
			model.addAttribute("OrderCount", pjDao.orderCount(vo));//구매여부
		}
		return "pj/detail";
	};
	
	
	@GetMapping("/selectCheck")//구매할 옵션 선택(확인)
	public String selectCheck(@RequestParam int optionsNo, Model model, HttpSession session) {
		Integer loginNo=(Integer) session.getAttribute("loginNo");
		if(loginNo==null) {
			return "redirect:/mem/login";
		}
		
		model.addAttribute("OptionsDto", optionsDao.selectOne(optionsNo));//선택중인 옵션
		return "pj/selectCheck";
	};
	
	@GetMapping("/order")
	public String order(@RequestParam int optionsNo, HttpSession session, Model model) {
		Integer loginNo=(Integer) session.getAttribute("loginNo");
		if(loginNo==null) {
			return "redirect:/mem/login";
		}
		OptionsDto optionsDto=optionsDao.selectOne(optionsNo);
		int pjNo=optionsDto.getOptionsPjNo();
		model.addAttribute("OptionsDto", optionsDao.selectOne(optionsNo));
		model.addAttribute("PjDto", pjDao.selectOne(pjNo));

		//현재 접속중인 계정이 등록해둔 배송지 목록을 저장해서 넘김
		int loginNo2=(int) session.getAttribute("loginNo");
		model.addAttribute("AddressDto",addressDao.selectList(loginNo2));
		
		
		return "pj/order";
	};
	
	
	@PostMapping("/order")
	public String order(@ModelAttribute OrdersDto ordersDto, @ModelAttribute AddressDto addressDto,
			@RequestParam int ordersOptionsNo, Model model, HttpSession session) {
		Integer loginNo=(Integer) session.getAttribute("loginNo");
		if(loginNo==null) {
			return "redirect:/mem/login";
		}
		int loginNo2=(int) session.getAttribute("loginNo");
		model.addAttribute("memNo", loginNo2);
		OptionsDto optionsDto=optionsDao.selectOne(ordersOptionsNo);
		int optionsPjNo=optionsDto.getOptionsPjNo();
		model.addAttribute("PjDto", pjDao.selectOne(optionsPjNo));
		model.addAttribute("OptionsDto", optionsDao.selectOne(ordersOptionsNo));
		ordersDao.insert(ordersDto);//주문 작성
		optionsDao.stockUpdate(ordersOptionsNo);//주문한 옵션의 재고를 1 깎음
		return "redirect:/pj/orderComplete";
	};
	
	@GetMapping("/orderComplete")
	public String orderComplete() {
		return "pj/orderComplete";
	}
	
	@GetMapping("/like")
	public String like(@RequestParam int pjNo, HttpSession session, RedirectAttributes attr) {
		int loginNo=(int) session.getAttribute("loginNo");
		LikesDto likesDto=new LikesDto();
		likesDto.setLikesMemNo(loginNo);
		likesDto.setLikesPjNo(pjNo);
		
		if(likesDao.check(likesDto)) {//이미 좋아요를 했을 경우
			likesDao.delete(likesDto);
		}
		else {//아직 좋아요를 하지 않았다면
			likesDao.insert(likesDto);
		}
		
		likesDao.refresh(pjNo);//좋아요 수를 갱신함
		attr.addAttribute("pjNo",pjNo);
		return "redirect:/pj/detail";
		
	};
	
	
	@GetMapping("/list")
	public String list(Model model, 
			@ModelAttribute(name="pjListSearchVo") PjListSearchVO vo) {
		
		//페이지 네비게이터를 위한 게시글 수를 구한 것
		int count = pjDao.count(vo);
		vo.setCount(count);
	
		model.addAttribute("list",pjDao.selectList(vo));
		model.addAttribute("amountCalList", pjDao.achievementRate());
		return "pj/list";
	};
	
	

}