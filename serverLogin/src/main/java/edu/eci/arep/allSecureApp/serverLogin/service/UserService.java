package edu.eci.arep.allSecureApp.serverLogin.service;

import edu.eci.arep.allSecureApp.serverLogin.model.User;

public interface UserService {
	
	User findByEmail(String email);
	
	User createUser(String email, String password);
	
}