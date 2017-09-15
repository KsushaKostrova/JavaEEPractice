package com.kostrova.tv.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
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
	@ManagedProperty(value="#{cartView}")
	@Inject
	private CartView cartView;
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
	private Map<Integer, Integer> goodsMax = new HashMap<Integer, Integer>();

	private Cart cart = new Cart();

	@PostConstruct
	public void init() {
		goods.addAll(goodDao.getGoods());
		setGoodsMax();
		cart.setUser(userDao.getUserByLogin(loginView.getUser().getLogin()));
		cart.setGoodOrderedQuantity(0);
		selectedGood = new Good();
		selectedGood.setQuantity(0);
	}
	
	public void upd() {
		goods.clear();
		goods.addAll(goodDao.getGoods());
		setGoodsMax();
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<Integer, Integer> getGoodsMax() {
		return goodsMax;
	}

	public void setGoodsMax() {
		for (Good good : goods) {
			goodsMax.put(good.getId(), good.getQuantity());
		}
	}

	public List<Good> getGoods() {
		return goods;
	}

	public void increaseChosenNumber() {
		int futureQuantity = selectedGood.getQuantity() + 1;
		if (futureQuantity <= goodsMax.get(selectedGood.getId())) {
			selectedGood.setQuantity(selectedGood.getQuantity() + 1);
		}
	}

	public void decreaseChosenNumber() {
		int currQuantity = selectedGood.getQuantity();
		if (currQuantity > 0) {
			selectedGood.setQuantity(currQuantity - 1);
		}
	}

	public void addToCart() {
		if (selectedGood.getQuantity() > 0 && !cartDao.goodIsInCart(selectedGood)) {
			cart.setId(null);
			cart.setGood(selectedGood);
			cart.setGoodOrderedQuantity(selectedGood.getQuantity());
			cartDao.addToCart(cart);
			cartView.upd();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"You have sucessfully added this good to your caret", null));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "You have chosen nothing", null));
		}
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
