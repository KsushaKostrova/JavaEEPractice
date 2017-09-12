package com.kostrova.tv.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.kostrova.tv.dto.Cart;
import com.kostrova.tv.dto.Good;
import com.kostrova.tv.service.ICartDao;

@Named
@SessionScoped
public class CartView implements Serializable {

	private static final long serialVersionUID = 1L;
	private Good[] selectedDraftGoods;
	private List<Cart> draftOrders;
	@Inject
	private LoginView loginView;
	@Inject
	private ICartDao cartDao;

	@PostConstruct
	public void init() {
		draftOrders = cartDao.getCartByLogin(loginView.getUser().getLogin());
	}

	public String goToMakeOrder() {
		if (selectedDraftGoods.length > 0) {
			return "make-order?faces-redirect=true";
		} else {
			return "personal-cart";
		}
	}

	public Good[] getSelectedDraftGoods() {
		return selectedDraftGoods;
	}

	public void setSelectedDraftGoods(Good[] selectedDraftGoods) {
		this.selectedDraftGoods = selectedDraftGoods;
	}

	public List<Cart> getDraftOrders() {
		return draftOrders;
	}

	public void setDraftOrders(List<Cart> draftOrders) {
		this.draftOrders = draftOrders;
	}

	public LoginView getLoginView() {
		return loginView;
	}

	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

	public ICartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(ICartDao cartDao) {
		this.cartDao = cartDao;
	}

}
