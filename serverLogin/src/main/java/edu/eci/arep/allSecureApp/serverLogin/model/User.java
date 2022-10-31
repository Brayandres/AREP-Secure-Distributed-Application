package edu.eci.arep.allSecureApp.serverLogin.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

import edu.eci.arep.allSecureApp.serverLogin.data.RoleEnum;

public class User {
	
	private static class idSequence {
		private static Object mutex = new Object();
		private static Long currentValue = -1L;
		private static Long getNext() {
			synchronized(mutex) {
				currentValue += 1;
				return currentValue;
			}
		}
	};
	
	private String id;
	private String email;
	private String passwordHash;
	private List<RoleEnum> roles;
	
	public User() {
		
	}
	
	public User(String email, String password) {
		this.id = String.valueOf(User.idSequence.getNext());
		this.email = email;
		this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
		this.roles = new ArrayList<>(Collections.singleton( RoleEnum.USER ));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash ='" + passwordHash + '\'' +
                '}';
    }
}