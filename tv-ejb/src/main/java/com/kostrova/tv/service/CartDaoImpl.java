package com.kostrova.tv.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.kostrova.tv.dto.Cart;
import com.kostrova.tv.dto.Good;
import com.kostrova.tv.dto.User;

@Stateless
public class CartDaoImpl implements ICartDao {

	@PersistenceContext(unitName = "TvStoreUnit")
	private EntityManager em;

	@Override
	public List<Cart> getCartByLogin(String login) {
		if (login == null || login.isEmpty()) {
			return new ArrayList<>();
		}
		return em.createNamedQuery("getCartByLogin", Cart.class).setParameter(1, login).getResultList();
	}

	@Override
	public void addToCart(Cart cart) {
		if (cart != null) {
			em.persist(cart);
		}
	}

	@Override
	public void removeFromCart(List<Cart> carts) {
		for (Cart cart : carts) {
			Cart c = em.find(Cart.class, cart.getId());
			em.remove(c);
		}
	}

	@Override
	public boolean goodIsInCart(Good good, User user) {
		try {
			em.createNamedQuery("getCartByGoodId", Cart.class).setParameter(1, good.getId())
					.setParameter(2, user.getId()).getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
}
