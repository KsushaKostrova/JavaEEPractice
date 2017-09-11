package com.kostrova.tv.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.kostrova.tv.dto.Order;

@Stateless
public class OrderDaoImpl implements IOrderDao {

	@PersistenceContext(unitName = "TvStoreUnit")
	private EntityManager em;

	@Override
	public List<Order> getOrdersByLogin(String login) {
		if (login == null || login.isEmpty()) {
			return new ArrayList<>();
		}
		return em.createNamedQuery("getOrdersByLogin", Order.class).setParameter(1, login).getResultList();
	}

	@Override
	public void addOrder(Order order) {
		if (order != null) {
			em.persist(order);
		}
	}
}
