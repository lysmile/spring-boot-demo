package com.smile.demo.sringbootdockerdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloDockerController {

	@RequestMapping("start")
	public String hello() {
		return "hello docker!";
	}
	
}

