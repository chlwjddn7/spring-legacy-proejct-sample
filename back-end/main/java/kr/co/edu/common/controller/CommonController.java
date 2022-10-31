package kr.co.edu.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.edu.common.service.CommonService;
import kr.co.edu.common.vo.Test;

@Controller
public class CommonController {

	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/test/jsp")
	public String testJsp (Model model) {
		Test test = new Test();
		
		List<Test> testList = commonService.testSelectList();
		System.out.println("/test/jsp");
		model.addAttribute("testList", testList);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			//Java Object to JSON;
			String jsonData = mapper.writeValueAsString(testList);
			System.out.println(jsonData);
			System.out.println(jsonData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/test";
	}
	
	@RequestMapping("/test/jsp2")
	public ModelAndView testJsp2 () {
		List<Test> testList = commonService.testSelectList();
		ModelAndView mav = new ModelAndView("/test");
		//mav.setViewName("/test");
		mav.addObject("testList", testList);
		return mav;
	}
	
	@RequestMapping("/test/json")
	public ModelAndView testJson () {
		List<Test> testList = commonService.testSelectList();
		ModelAndView mav = new ModelAndView("jsonView");
		//mav.setViewName("/test");
		mav.addObject("testList", testList);
		return mav;
	}
	
}
