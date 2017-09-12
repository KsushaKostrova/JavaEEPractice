package com.kostrova.tv.web;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.kostrova.tv.dto.Good;
import com.kostrova.tv.dto.Order;
import com.kostrova.tv.service.IOrderDao;

@Named
@ConversationScoped
public class OrderingView implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Good[] draftGoods;
	@Inject
	private CartView cartView;
	@Inject
	private IOrderDao orderDao;
	@Inject
	private LoginView loginView;
	private Order order; 
	
	public void order() {
		
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Good[] getDraftGoods() {
		return draftGoods;
	}

	public void setDraftGoods(Good[] draftGoods) {
		this.draftGoods = draftGoods;
	}

	public CartView getCartView() {
		return cartView;
	}

	public void setCartView(CartView cartView) {
		this.cartView = cartView;
	}

	public IOrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public LoginView getLoginView() {
		return loginView;
	}

	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}
}
