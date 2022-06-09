package com.qa.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Team not found")
public class TeamException extends Exception {

	private static final long serialVersionUID = -8953809291229383157L;

}
