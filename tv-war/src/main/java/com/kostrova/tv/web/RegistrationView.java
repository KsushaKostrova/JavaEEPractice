package com.kostrova.tv.web;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
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
	private String confirmedPassword = "";

	// @Loggable
	public String register() {
		if (user.getPassword().equals(confirmedPassword)) {
			try {
				if (userDao.getUserByLogin(user.getLogin()) == null) {
					try {
						userDao.addUser(user);
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
								"You have been sucessfully registered", null));
						loginView.setUser(user);
						loginView.setIsAuthenticated(true);
						return "personal-orders?faces-redirect=true";
					} catch (PersistenceException e) {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Unable to register user", e.getMessage()));
						return null;
					}

				} else {

					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Such user already exists", null));
					return null;
				}
			} catch (NoResultException ex) {
				return null;
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords do not match", null));
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

	public String getConfirmedPassword() {
		return confirmedPassword;
	}

	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}

}
