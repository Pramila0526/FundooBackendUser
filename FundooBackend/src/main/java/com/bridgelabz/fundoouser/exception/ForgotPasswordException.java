package com.bridgelabz.fundoouser.exception;

/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Defining Method Exception in Forgetting the password
 *
 *********************************************************************************************************/

public class ForgotPasswordException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ForgotPasswordException(String message) {
		super(message);
	}
}
