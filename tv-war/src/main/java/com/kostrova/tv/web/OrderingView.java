package com.kostrova.tv.web;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.kostrova.tv.dto.Address;
import com.kostrova.tv.dto.Good;
import com.kostrova.tv.dto.Order;
import com.kostrova.tv.dto.User;
import com.kostrova.tv.service.IOrderDao;
import com.kostrova.tv.service.IUserDao;

@Named
@ConversationScoped
public class OrderingView implements Serializable {
	private static final long serialVersionUID = 1L;

	private Good[] draftGoods;
	@Inject
	private CartView cartView;
	@Inject
	private IOrderDao orderDao;
	@Inject
	private IUserDao userDao;
	@Inject
	private LoginView loginView;
	private Order order = new Order();
	private Address address = new Address();
	private User user;
	private String userName;
	@Inject
	private Conversation conversation;
	private String goodNames = "";
	private String quantities = "";

	@PostConstruct
	public void init() {
		user = userDao.getUserByLogin(loginView.getUser().getLogin());
		userName = user.getFirstName() + " " + user.getLastName();
		if (!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
			conversation.begin();
		}
	}

	public void makeOrder() {

	}

	public String next() {
		order.setAddress(address);
		order.setUser(user);
		order.setGoods(cartView.getSelectedGoods());
		order.setOrderTime(LocalDateTime.now());
		for (Good good : order.getGoods()) {
			goodNames = goodNames + good.getName();
		}
		for (Good good : order.getGoods()) {
			quantities = quantities + good.getQuantity();
		}
		return "ordering_second_page?faces-redirect=true";
	}

	public String endConversation() {
		orderDao.addOrder(order);
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "personal-orders?faces-redirect=true";
	}

	public String getGoodNames() {
		return goodNames;
	}

	public void setGoodNames(String goodNames) {
		this.goodNames = goodNames;
	}

	public String getQuantities() {
		return quantities;
	}

	public void setQuantities(String quantities) {
		this.quantities = quantities;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
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
