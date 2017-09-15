package com.kostrova.tv.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.kostrova.tv.dto.Good;
import com.kostrova.tv.dto.User;

@Stateless
public class GoodDaoImpl implements IGoodDao {
	@PersistenceContext(unitName = "TvStoreUnit")
	private EntityManager em;

	@Override
	public List<Good> getGoods() {
		return em.createNamedQuery("getGoods", Good.class).getResultList();
	}

	@Override
	public Good getGoodById(Integer id, User user) {
		Good good = (Good) em.createNamedQuery("getGoodByIdFromCart").setParameter(1, id).setParameter(2, user.getId())
				.getSingleResult();
		return good;
	}

	@Override
	public void updateQuantitiesInTable(List<Good> goods) {
		for (Good good : goods) {
			Good storedGood = em.find(Good.class, good.getId());
			storedGood.setQuantity(storedGood.getQuantity() - good.getQuantity());
			em.merge(storedGood);
		}
	}
}
