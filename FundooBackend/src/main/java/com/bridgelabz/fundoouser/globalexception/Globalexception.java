package com.bridgelabz.fundoouser.globalexception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import com.bridgelabz.fundoouser.exception.LoginException;
import com.bridgelabz.fundoouser.exception.RegistrationExcepton;
import com.bridgelabz.fundoouser.exception.TokenException;
import com.bridgelabz.fundoouser.exception.ValidateUserException;
import com.bridgelabz.fundoouser.exception.ValidationException;
import com.bridgelabz.fundoouser.message.Messages;
import com.bridgelabz.fundoouser.exception.ForgotPasswordException;
import com.bridgelabz.fundoouser.response.Response;

@ControllerAdvice
public class Globalexception {
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<Response> loginException(Exception e) {

		return new ResponseEntity<Response>(new Response(Messages.OK, e.getMessage(), "Please Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(RegistrationExcepton.class)
	public ResponseEntity<Response> registrationExcepton(Exception e) {

		return new ResponseEntity<Response>(new Response(Messages.BAD_REQUEST, e.getMessage(), "Please Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(TokenException.class)
	public ResponseEntity<Response> tokenException(Exception e) {

		return new ResponseEntity<Response>(new Response(Messages.BAD_REQUEST, e.getMessage(), "Please Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Response> validationException(Exception e) {

		return new ResponseEntity<Response>(new Response(Messages.BAD_REQUEST, e.getMessage(), "Please Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ValidateUserException.class)
	public ResponseEntity<Response> ValidateUserException(Exception e) {

		return new ResponseEntity<Response>(new Response(Messages.BAD_REQUEST, e.getMessage(), "Please Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ForgotPasswordException.class)
	public ResponseEntity<Response> ForgotPasswordException(Exception e) {

		return new ResponseEntity<Response>(new Response(Messages.BAD_REQUEST, e.getMessage(), "Please Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> methodArgumentNotValidException(Exception e) {

		return new ResponseEntity<Response>(new Response(Messages.ENTER_EMAIL, e.getMessage(), "Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Response> missingServletRequestParameterException(Exception e) {

		return new ResponseEntity<Response>(new Response(Messages.ENTER_EMAIL, e.getMessage(), "Try Again"), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
