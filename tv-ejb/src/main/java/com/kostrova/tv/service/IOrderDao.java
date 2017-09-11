package com.kostrova.tv.service;

import java.util.List;

import com.kostrova.tv.dto.Order;

public interface IOrderDao {
	
	List<Order> getOrdersByLogin(String login);
	
	void addOrder(Order order);
}
