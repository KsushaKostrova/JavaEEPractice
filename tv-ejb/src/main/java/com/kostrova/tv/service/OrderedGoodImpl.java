package com.kostrova.tv.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.kostrova.tv.dto.OrderedGood;

@Stateless
public class OrderedGoodImpl implements IOrderedGood{

	@PersistenceContext(unitName = "TvStoreUnit")
	private EntityManager em;
	
	@Override
	public Integer getQuantityById(Integer goodId, Integer orderId) {
		return  (Integer) em.createNamedQuery("getQuantityByIdOrderId").setParameter(1, goodId).setParameter(2, orderId).getSingleResult();
	}

	@Override
	public void addOrderedGood(OrderedGood orderedGood) {
		em.persist(orderedGood);
	}

}
