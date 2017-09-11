package com.kostrova.tv.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.kostrova.tv.dto.Order;
import com.kostrova.tv.service.IOrderDao;

@Named
@SessionScoped
public class UserData implements Serializable {
	private static final long serialVersionUID = -2426465981544305605L;

	@Inject
	private LoginView loginView;
	@Inject
	private IOrderDao orderDao;
	private List<Order> orders;

	@PostConstruct
	public void init() {
		orders = orderDao.getOrdersByLogin(loginView.getUser().getLogin());
	}

	public LoginView getLoginView() {
		return loginView;
	}

	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

	public IOrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
