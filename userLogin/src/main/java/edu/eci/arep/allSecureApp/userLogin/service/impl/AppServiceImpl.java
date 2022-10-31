package edu.eci.arep.allSecureApp.userLogin.service.impl;

import org.springframework.stereotype.Service;

import edu.eci.arep.allSecureApp.userLogin.external.ServerConnection;
import edu.eci.arep.allSecureApp.userLogin.service.AppService;

@Service("appService")
public class AppServiceImpl implements AppService {

	@Override
	public String connetToServer() throws Exception {
		ServerConnection.trustAllCerts();
		ServerConnection.loginToServer();
		var response = ServerConnection.getServerMessage();
		return response;
	}
}