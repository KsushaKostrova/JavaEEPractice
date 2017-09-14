package com.kostrova.tv.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.kostrova.tv.dto.Address;

@Stateless
public class AddressDaoImpl implements IAddressDao {

	@PersistenceContext(unitName = "TvStoreUnit")
	private EntityManager em;

	@Override
	public void addAddress(Address address) {
		if (address != null) {
			em.persist(address);
		}
	}
}
