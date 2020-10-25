package com.ssafy.homework.model.mapper;

import java.util.List;

import com.ssafy.homework.model.dto.product;



public interface productMapper {

	List<product> select();
	void insert(product product);
}
