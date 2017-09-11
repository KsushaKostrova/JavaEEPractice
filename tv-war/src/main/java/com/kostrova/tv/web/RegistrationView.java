package com.kostrova.tv.web;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import com.kostrova.tv.dto.User;
import com.kostrova.tv.service.IUserDao;

@Named("regView")
@RequestScoped
public class RegistrationView {

	@Inject
	private IUserDao userDao;
	@Inject
	private LoginView loginView;
	private User user = new User();

//	@Loggable
	public String register() {
		try {
			userDao.addUser(user);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "You have been sucessfully registered", null));
			loginView.setUser(user);
			loginView.setIsAuthenticated(true);
			return "personal-orders?faces-redirect=true";
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to register user", e.getMessage()));
			return null;
		}
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public LoginView getLoginView() {
		return loginView;
	}

	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
