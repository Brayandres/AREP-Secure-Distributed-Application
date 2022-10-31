package edu.eci.arep.allSecureApp.userLogin.exception;

import org.springframework.http.HttpStatus;

import edu.eci.arep.allSecureApp.userLogin.error.ServerErrorResponseDto;

public class InternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = -5632608256392096224L;
	
	private final ServerErrorResponseDto serverErrorResponseDto;
    private final HttpStatus httpStatus;

    public InternalServerErrorException( ServerErrorResponseDto serverErrorResponseDto, HttpStatus httpStatus ) {
        this.serverErrorResponseDto = serverErrorResponseDto;
        this.httpStatus = httpStatus;
    }
}