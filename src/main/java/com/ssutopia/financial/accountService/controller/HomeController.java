package com.ssutopia.financial.accountService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeController {

	//used for health check
	@GetMapping("/")
	public ResponseEntity<String> homePage() {
		return new ResponseEntity<>("Hello World! (Account Service)", HttpStatus.OK);
	}
	
}
