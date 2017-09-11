package com.kostrova.tv.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kostrova.tv.dto.User;
import com.kostrova.tv.service.IUserDao;

@Named
@SessionScoped
public class LoginView implements Serializable {
	private static final long serialVersionUID = -6531568794924648482L;
	private static final Logger LOGGER = LogManager.getLogger(LoginView.class);

	@Inject
	private IUserDao userDao;
	private User user = new User();
	private Boolean isAuthenticated = false;
	
	@Loggable
//	@Interceptors(MyLogger.class)
	public String login() {		
		LOGGER.info("SDFUKGBVKHBDFCHGVJBKJKHJVGFF");
		if (userDao.isPasswordCorrect(user.getLogin(), user.getPassword())) {
			isAuthenticated = true;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully authenticated", null));
			return "personal-orders?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong login or password", null));
			return null;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getIsAuthenticated() {
		return isAuthenticated;
	}

	public void setIsAuthenticated(Boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

}
