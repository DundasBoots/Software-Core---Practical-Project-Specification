package com.qa.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {

	@GetMapping(value = "/home")
	public String home() {
		return "frontend/html/index.html";
	}
	@GetMapping(value = "/")
	public String homeSlash() {
		return "frontend/html/index.html";
	}
}
