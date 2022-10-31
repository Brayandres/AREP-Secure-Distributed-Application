package edu.eci.arep.allSecureApp.serverLogin.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.eci.arep.allSecureApp.serverLogin.model.User;
import edu.eci.arep.allSecureApp.serverLogin.service.UserService;

@Service("userService")
public class UserServiceHashMap implements UserService {
	
	private Map<String, User> users = new HashMap<String, User>();
	
	public UserServiceHashMap() {
		// This is an intentionally made security flaw, actually trying to represent a user stored in a secure database
		var serverEmail = "server@mail.com";
		var serverPassword = "SeeYouBaby";
		createUser(serverEmail, serverPassword);
	}
	
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