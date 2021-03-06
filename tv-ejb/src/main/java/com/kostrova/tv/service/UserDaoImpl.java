package com.kostrova.tv.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.kostrova.tv.dto.User;

@Stateless
public class UserDaoImpl implements IUserDao {

	@PersistenceContext(unitName = "TvStoreUnit")
	private EntityManager em;

	@Override
	public void addUser(User user) {
		if (user != null) {
			em.persist(user);
		}
	}

	@Override
	public boolean isPasswordCorrect(String login, String password) {
		if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
			return false;
		}

		List<User> result = em.createNamedQuery("getByLoginAndPassword", User.class).setParameter(1, login)
				.setParameter(2, password).getResultList();
		return (result != null && result.size() == 1);
	}

	@Override
	public User getUserByLogin(String login) throws NoResultException {
		if (login == null || login.isEmpty()) {
			return null;
		}

		User result = null;
		try {
			result = em.createNamedQuery("getByLogin", User.class).setParameter(1, login).getSingleResult();
			return result;
		} catch (NoResultException ex) {
			throw ex;
		}
	}

	@Override
	public boolean userExists(String login) {
		try {
			em.createNamedQuery("getByLogin", User.class).setParameter(1, login).getSingleResult();
			return true;
		} catch (NoResultException ex) {
			return false;
		}
	}
}
