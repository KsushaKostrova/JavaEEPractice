package com.kostrova.tv.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.kostrova.tv.dto.Good;

@Stateless
public class GoodDaoImpl implements IGoodDao {
	@PersistenceContext(unitName = "TvStoreUnit")
	private EntityManager em;

	@Override
	public List<Good> getGoods() {
		return em.createNamedQuery("getGoods", Good.class).getResultList();
	}

}
