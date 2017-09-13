package com.kostrova.tv.service;

import java.util.List;

import com.kostrova.tv.dto.Good;

public interface IGoodDao {

	List<Good> getGoods();
	
	Good getGoodById(Integer id);
}
