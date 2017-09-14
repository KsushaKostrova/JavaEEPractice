package com.kostrova.tv.service;

import java.util.List;

import com.kostrova.tv.dto.Cart;

public interface ICartDao {

	void addToCart(Cart caret);

	List<Cart> getCartByLogin(String login);
	void removeFromCart(List<Cart> carts);
}
