package com.kostrova.tv.service;

import com.kostrova.tv.dto.User;

public interface IUserDao {
	
	void addUser(User user);
	
	boolean isPasswordCorrect(String login, String password);

}
