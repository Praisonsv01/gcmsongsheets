package com.gcm.songs;

import java.util.Collections;

import javax.servlet.http.HttpServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GcmsongsheetsApplication extends HttpServlet{

	public static void main(String[] args) {
		String port = System.getenv().getOrDefault("X_ZOHO_CATALYST_LISTEN_PORT", "3000");
		SpringApplication app = new SpringApplication(GcmsongsheetsApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", port));
		app.run(args);
	}

}
