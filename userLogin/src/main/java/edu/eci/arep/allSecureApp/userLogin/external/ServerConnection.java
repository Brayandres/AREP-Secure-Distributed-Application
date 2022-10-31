package edu.eci.arep.allSecureApp.userLogin.external;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerConnection {
	
	private static final String serverEmail = System.getenv("SERVER-EMAIL");
	private static final String serverPassword = System.getenv("SERVER-PSWD");
	private static String serverToken;
	private static boolean isAlreadyLogged = false;
	private static boolean areCertsConfigured = false;
	private static final String HOST = System.getenv("AWS-SERVER-LOGIN");
	
	public static void loginToServer() {
		if (! isAlreadyLogged) {
			String uri = "/auth";
			String params = "email="+serverEmail+"&password="+serverPassword+"";
			byte[] postData = params.getBytes();
			try {
				var url = new URL(HOST+uri);
				var connection = (HttpsURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");
				connection.setRequestProperty("User-Agent", "Java client");
				connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				try (var output = new DataOutputStream(connection.getOutputStream())) {
					output.write(postData);
				}
				var input = (InputStream) connection.getInputStream();
				var response = new String(input.readAllBytes(), StandardCharsets.UTF_8);
				var values = new ObjectMapper().readValue(response, HashMap.class);
				serverToken = (String) values.get("token");
				input.close();
				connection.disconnect();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getServerMessage() {
		var response = "";
		if (serverToken != null) {
			var uri = "/server/msg";
			try {
				URL url = new URL(HOST+uri);
				HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Authorization","Bearer "+serverToken);
				connection.setRequestProperty("Content-Type", "application/json");
				InputStream input = (InputStream) connection.getInputStream();
				response = new String(input.readAllBytes(), StandardCharsets.UTF_8);				
				input.close();
				connection.disconnect();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return response;
	}
	
	public static void trustAllCerts() throws Exception {
        if (!areCertsConfigured) {
			TrustManager[] trustAllCerts = new TrustManager[] {
	        	new X509TrustManager() {
		            @Override
		            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		                return null;
		            }
		            @Override
		            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
		            @Override
		            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
	        	}
	        };
	        // Install the all-trusting trust manager
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCerts, new java.security.SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        // Create all-trusting host name verifier
	        HostnameVerifier allHostsValid = new HostnameVerifier() {
	            @Override
	            public boolean verify(String hostname, SSLSession session) {
	                return true;
	            }
	        };
	        // Install the all-trusting host verifier
	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	        areCertsConfigured = true;
        }
    }
}