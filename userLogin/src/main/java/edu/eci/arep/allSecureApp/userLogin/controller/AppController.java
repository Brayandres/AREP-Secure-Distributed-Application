package edu.eci.arep.allSecureApp.userLogin.controller;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arep.allSecureApp.userLogin.service.AppService;

@RestController
@RequestMapping("/app")
public class AppController {
	
	@Resource(name = "appService")
	private AppService appService;
	
	@RequestMapping(
		value = "/connect",
		method = RequestMethod.GET,
		produces = "application/json"
	)
    public ResponseEntity<?> connectToServer() {
		try {
			var response = appService.connetToServer();
			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			var response = new HashMap<String, String>();
			response.put("msg", "NOT AUTHORIZED");
			Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, e);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
    }
}