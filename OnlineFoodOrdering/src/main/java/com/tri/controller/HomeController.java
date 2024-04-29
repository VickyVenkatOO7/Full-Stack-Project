package com.tri.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
public class HomeController {
	
	@GetExchange
	public ResponseEntity<String> HomeController() {
		return new ResponseEntity<>("Welcome to food delivery project", HttpStatus.OK);
	}
	
}
