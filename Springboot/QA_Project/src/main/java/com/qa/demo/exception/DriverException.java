package com.qa.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Driver not found")
public class DriverException extends Exception {

	private static final long serialVersionUID = -221782165597573940L;

}
