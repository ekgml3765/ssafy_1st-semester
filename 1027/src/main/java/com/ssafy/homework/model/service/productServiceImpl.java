package com.ssafy.homework.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.homework.model.dto.product;
import com.ssafy.homework.model.mapper.productMapper;


@Service
public class productServiceImpl {

	@Autowired
	private productMapper dao;
	
	/*
	 * @Autowired private sqlSession session; session.getMapper() ; 이렇게 했는데 인터페이스 객체
	 * 다이렉트로 오토와이트함. 여기선
	 * 이렇게 하려면 인터페이스를 빈으로 등록해줘야해.
	 */

	public List<product> productList() throws Exception {
		return dao.select();
	}


	public void productSave(product product) throws Exception {
		dao.insert(product);
	}

	
}
