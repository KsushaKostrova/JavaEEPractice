package com.kostrova.tv.web;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import com.kostrova.tv.dto.Address;
import com.kostrova.tv.dto.Good;
import com.kostrova.tv.dto.Order;
import com.kostrova.tv.dto.OrderedGood;
import com.kostrova.tv.dto.User;
import com.kostrova.tv.service.ICartDao;
import com.kostrova.tv.service.IOrderDao;
import com.kostrova.tv.service.IOrderedGood;
import com.kostrova.tv.service.IUserDao;

@Named
//@ConversationScoped
@ViewScoped
public class OrderingView implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManagedProperty(value="#{cartView}")
	@Inject
	private CartView cartView;
	@Inject
	private ICartDao cartDao;
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
	@Inject
	private IOrderedGood orderedG;

	@PostConstruct
	public void init() {
		user = userDao.getUserByLogin(loginView.getUser().getLogin());
		userName = user.getFirstName() + " " + user.getLastName();
		if (!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
			conversation.begin();
		}
		order.setUser(user);
		order.setGoods(cartView.getSelectedGoods());
		order.setOrderTime(LocalDateTime.now());
		for (Good good : order.getGoods()) {
			goodNames = goodNames + good.getName();
		}
		for (Good good : order.getGoods()) {
			quantities = quantities + good.getQuantity();
		}
	}

	public void addOrderedQuantities() {
		for (Good good : order.getGoods()) {
			OrderedGood orderedGood = new OrderedGood();
			orderedGood.setGoodId(good.getId());
			orderedGood.setQuantity(good.getQuantity());
			orderedGood.setOrderId(order.getId());
			orderedG.addOrderedGood(orderedGood);
		}
	}

	public String onFlowProcess(FlowEvent event) {
		return event.getNewStep();
    }
	
	private void updateCartView(){
		cartView.upd();
}
	
	public ICartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(ICartDao cartDao) {
		this.cartDao = cartDao;
	}

	public String endConversation() {
	//	addressDao.addAddress(address);
		order.setAddress(address);
		orderDao.addOrder(order);		
		cartDao.removeFromCart(cartView.getDraftOrders());
		addOrderedQuantities();
		updateCartView();
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
