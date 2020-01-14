package com.bridgelabz.fundoouser.service;
import com.bridgelabz.fundoouser.dto.ForgotPasswordDto;
import com.bridgelabz.fundoouser.dto.LoginDto;
import com.bridgelabz.fundoouser.dto.RegistrationDto;
import com.bridgelabz.fundoouser.dto.ResetPasswordDto;
import com.bridgelabz.fundoouser.response.Response;
/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Messages for identifying the Status of Implementation
 *
 *********************************************************************************************************/
public interface UserService {
	public Response Register(RegistrationDto regdto); // To register the new user

	public Response loginUser(LoginDto logindto); // For User Login

	public Response setPassword(ResetPasswordDto setPasswordDto, String token); // For Setting the pasword

	public Response forgotPassword(ForgotPasswordDto forgetPasswordDto);   // To recover 
}