package com.kostrova.tv.service;

import java.util.List;

import com.kostrova.tv.dto.Cart;
import com.kostrova.tv.dto.Good;

public interface ICartDao {

	void addToCart(Cart caret);

	List<Cart> getCartByLogin(String login);
	
	void removeFromCart(List<Cart> carts);
	
	boolean goodIsInCart(Good good);
}
