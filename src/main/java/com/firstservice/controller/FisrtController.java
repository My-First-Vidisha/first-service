package com.firstservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/first")
@CrossOrigin(origins = "*")
public class FisrtController {

	@Autowired
	private WebClient webClient;

	@GetMapping
	public ResponseEntity<String> getMessage() {
		return new ResponseEntity<String>("hello, From First Service", HttpStatus.OK);
	}

	@GetMapping("/call")
	public ResponseEntity<String> getCallMessage() {

		Mono<String> mono = webClient.get().uri("http://localhost:8084/second/new").retrieve().bodyToMono(String.class);

		return new ResponseEntity<String>("First Service Kya bat bava !!!!!!!   " + mono.block(), HttpStatus.OK);
	}
}
