package com.ssafy.homework.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssafy.homework.model.dto.product;
import com.ssafy.homework.model.service.productServiceImpl;

@Controller
public class ProductController {


	@Autowired
	private productServiceImpl service;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@GetMapping("/register")
	public String registerForm() {
		logger.debug("등록하러 가자. 실행하자!");
		return "register";
	}
	
	@PostMapping("/register")
	public String register(product product, Model model) {
		System.out.println(product);
		try {
			service.productSave(product);
			model.addAttribute("Productlist", service.productList());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("에러발생");
		}
		return "list";
	}
}
