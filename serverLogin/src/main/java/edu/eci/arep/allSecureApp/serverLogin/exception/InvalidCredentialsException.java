package edu.eci.arep.allSecureApp.serverLogin.exception;

import org.springframework.http.HttpStatus;

import edu.eci.arep.allSecureApp.serverLogin.error.ErrorCodeEnum;
import edu.eci.arep.allSecureApp.serverLogin.error.ServerErrorResponseDto;

public class InvalidCredentialsException extends InternalServerErrorException {

	private static final long serialVersionUID = -5643645800604090793L;

	public InvalidCredentialsException() {
		super(
			new ServerErrorResponseDto(
				"Invalid username or password",
				ErrorCodeEnum.INVALID_USER_CREDENTIALS,
				HttpStatus.UNAUTHORIZED
			),
			HttpStatus.UNAUTHORIZED
		);
    }
}