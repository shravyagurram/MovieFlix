package io.egen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Details Already Exist")
public class MovieDetailsExistsException extends Exception{

	private static final long serialVersionUID = 1L;

}
