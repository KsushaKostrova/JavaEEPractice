package com.kostrova.tv.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.kostrova.tv.dto.Cart;
import com.kostrova.tv.dto.Good;
import com.kostrova.tv.dto.User;
import com.kostrova.tv.service.ICartDao;
import com.kostrova.tv.service.IGoodDao;
import com.kostrova.tv.service.IUserDao;

@Named
@SessionScoped
public class ShopView implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Good> goods = new ArrayList<Good>();

	private Good selectedGood;
	@Inject
	IGoodDao goodDao;
	@Inject
	IUserDao userDao;
	User user;
	@Inject
	private ICartDao cartDao;
	@Inject
	private LoginView loginView;
	
	private Cart cart = new Cart();

	@PostConstruct
	public void init() {
		goods.addAll(goodDao.getGoods());
		cart.setUser(userDao.getUserByLogin(loginView.getUser().getLogin()));
		selectedGood = new Good();
		selectedGood.setQuantity(0);
	}

	public List<Good> getGoods() {
		return goods;
	}

	public void increaseChosenNumber() {
		selectedGood.setQuantity(selectedGood.getQuantity()+1);
	}

	public void decreaseChosenNumber() {
		int currQuantity = selectedGood.getQuantity();
		if (currQuantity > 0)
			selectedGood.setQuantity(currQuantity-1);
	}
	
	public void addToCart() {
		cart.setGood(selectedGood);
		cartDao.addToCart(cart);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "You have sucessfully added this good to your caret", null));
	}

	public IGoodDao getGoodDao() {
		return goodDao;
	}

	public LoginView getLoginView() {
		return loginView;
	}

	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

	public Good getSelectedGood() {
		return selectedGood;
	}

	public void setSelectedGood(Good selectedGood) {
		this.selectedGood = selectedGood;
	}

	public ICartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(ICartDao cartDao) {
		this.cartDao = cartDao;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

	public void setGoodDao(IGoodDao goodDao) {
		this.goodDao = goodDao;
	}

}
