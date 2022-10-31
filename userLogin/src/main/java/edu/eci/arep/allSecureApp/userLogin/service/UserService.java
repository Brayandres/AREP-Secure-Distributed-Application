package edu.eci.arep.allSecureApp.userLogin.service;

import edu.eci.arep.allSecureApp.userLogin.model.User;

public interface UserService {
	
	User findByEmail(String email);
	
	User createUser(String email, String password);
	
}