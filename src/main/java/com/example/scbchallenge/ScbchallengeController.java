package com.example.scbchallenge;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScbchallengeController {
	
	@RequestMapping("/")
	public String index() {
		return "This is Index Page";
	}
	
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
	
	@GetMapping("/encodeTxt")
	public String encodeTxt(@RequestParam(value = "funnyStr") String funnyStr) {
		ScbchallengeApplication app = new ScbchallengeApplication();
		String response = app.encodeTxt(funnyStr);
		return response;
	}
	
}
