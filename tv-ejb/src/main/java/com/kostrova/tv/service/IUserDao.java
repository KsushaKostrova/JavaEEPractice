package com.kostrova.tv.service;

import java.util.List;

import com.kostrova.tv.dto.User;

public interface IUserDao {
	
	void addUser(User user);
	
	boolean isPasswordCorrect(String login, String password);

	User getUserByLogin(String login);
}
