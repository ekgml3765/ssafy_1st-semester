package com.ssafy.homework.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssafy.homework.model.dto.product;

@Controller
public class ProductController {


	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@GetMapping("/register")
	public String registerForm() {
		logger.debug("등록하러 가자. 실행하자!");
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute product product) {
		logger.debug("등록한다!");
		logger.debug("product :{}", product);
		return "list";
	}
}
