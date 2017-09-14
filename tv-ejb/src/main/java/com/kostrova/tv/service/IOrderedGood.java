package com.kostrova.tv.service;

import com.kostrova.tv.dto.OrderedGood;

public interface IOrderedGood {

	Integer getQuantityById(Integer goodId, Integer orderId);
	
	void addOrderedGood(OrderedGood orderedGood);
}
