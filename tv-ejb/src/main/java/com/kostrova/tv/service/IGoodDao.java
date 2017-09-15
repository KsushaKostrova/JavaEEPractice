package com.kostrova.tv.service;

import java.util.List;

import com.kostrova.tv.dto.Good;
import com.kostrova.tv.dto.User;

public interface IGoodDao {

	List<Good> getGoods();
	
	Good getGoodById(Integer id, User user);

	void updateQuantitiesInTable(List<Good> goods);
}
