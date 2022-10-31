package edu.eci.arep.allSecureApp.serverLogin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerController {
	
	@RequestMapping(
		value = "/msg",
		method = RequestMethod.GET,
		produces = "application/json"
	)
    public ResponseEntity<?> serverMessage() {
		Map<String, String> response = new HashMap<String, String>();
		response.put("msg", "Connected to Server, great!");
    	return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}