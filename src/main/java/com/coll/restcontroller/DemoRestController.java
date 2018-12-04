package com.coll.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController 
{
	@GetMapping("/demo")
	public ResponseEntity<String> demoImpl()
	{
		System.out.println("----demo Restful API_---");
		return new ResponseEntity("Welcom to Rest Controller",HttpStatus.OK);
	}
}
