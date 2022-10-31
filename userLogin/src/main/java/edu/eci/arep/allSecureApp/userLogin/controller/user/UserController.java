package edu.eci.arep.allSecureApp.userLogin.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arep.allSecureApp.userLogin.controller.auth.LoginDto;
import edu.eci.arep.allSecureApp.userLogin.model.User;
import edu.eci.arep.allSecureApp.userLogin.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseEntity<User> findByEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByEmail(email));
    }
    
    @PostMapping
    public ResponseEntity<User> create(@RequestBody LoginDto data) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.createUser(data.getEmail(), data.getPassword()));
    }
}