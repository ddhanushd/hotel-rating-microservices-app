package com.user.microservices.service;

import java.util.List;

import com.user.microservices.entities.User;

public interface UserService {
	
	User saveUser(User user);
	List<User>getAllUsers();
	User getUserById(String userId);
	User updateUser(User user, String userId);
	void deleteUser(String userId);

}
