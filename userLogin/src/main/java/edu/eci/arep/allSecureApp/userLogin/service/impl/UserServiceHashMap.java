package edu.eci.arep.allSecureApp.userLogin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.eci.arep.allSecureApp.userLogin.model.User;
import edu.eci.arep.allSecureApp.userLogin.service.UserService;

@Service("userService")
public class UserServiceHashMap implements UserService {
	
	private Map<String, User> users = new HashMap<String, User>();
	
	@Override
	public User findByEmail(String email) {
		User user = users.get(email);
		return user;
	}
	
	@Override
	public User createUser(String email, String password) {
		User user = new User(email, password);
		users.put(email, user);
		return users.get(email);
	}
}