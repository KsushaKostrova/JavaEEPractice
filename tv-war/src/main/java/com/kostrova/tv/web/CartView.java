package com.kostrova.tv.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import com.kostrova.tv.dto.Cart;
import com.kostrova.tv.dto.Good;
import com.kostrova.tv.service.ICartDao;
import com.kostrova.tv.service.IGoodDao;

@Named
@ApplicationScoped
public class CartView implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<String> selectedDraftGoods;
	private List<Good> selectedGoods = new ArrayList<Good>();
	private List<Cart> draftOrders;
	@Inject
	private LoginView loginView;
	@Inject
	private ICartDao cartDao;
	@Inject
	private IGoodDao goodDao;

	@PostConstruct
	public void init() {
		draftOrders = cartDao.getCartByLogin(loginView.getUser().getLogin());
	}

	public String goToMakeOrder() {
		if (selectedDraftGoods.size() > 0) {
			for (String id : selectedDraftGoods) {
				selectedGoods.add(goodDao.getGoodById(Integer.parseInt(id.trim())));
	        }
			return "make-order?faces-redirect=true";
		} else {
			new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nothing is selected", null);
			return "personal-cart";
		}
	}

	public List<Good> getSelectedGoods() {
		return selectedGoods;
	}

	public void setSelectedGoods(List<Good> selectedGoods) {
		this.selectedGoods = selectedGoods;
	}

	public IGoodDao getGoodDao() {
		return goodDao;
	}

	public void setGoodDao(IGoodDao goodDao) {
		this.goodDao = goodDao;
	}

	public List<String> getSelectedDraftGoods() {
		return selectedDraftGoods;
	}

	public void setSelectedDraftGoods(List<String> selectedDraftGoods) {
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
