package com.bridgelabz.fundoouser.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoouser.dto.ForgotPasswordDto;
import com.bridgelabz.fundoouser.dto.LoginDto;
import com.bridgelabz.fundoouser.dto.RegistrationDto;
import com.bridgelabz.fundoouser.dto.ResetPasswordDto;
import com.bridgelabz.fundoouser.message.Messages;
import com.bridgelabz.fundoouser.response.Response;
import com.bridgelabz.fundoouser.service.UserServiceImplementation;
/**********************************************************************************************************
 * @author 	:Pramila Tawari 
 * Purpose	:The controller invokes a business class to process business-related tasks, 
 * 			 and then redirects the client to a logical view name, which is resolved by 
 * 			 Spring’s dispatcher servlet in order to render results or output. That completes 
 * 			 a round trip of a typical request-response cycle.
 *
 *********************************************************************************************************/

@RestController
public class UserController 
{
      @Autowired
      UserServiceImplementation userServiceImp;
	
      @GetMapping("/demo")
      public String demo() 
      {
    	  return "Hello User!!";
      }
      
    // API for registering the user into the database. 
	@PostMapping("/register")
	public ResponseEntity<Response> Register(@Valid @RequestBody RegistrationDto regDto)
	{
		return new ResponseEntity<Response>(userServiceImp.Register(regDto), HttpStatus.OK); // give response for user 200
	}
	
	// API for validating the user into the database. 
	@PostMapping("/validate")
	public ResponseEntity<Response> validate(@Valid @RequestParam String token) 
	{
		return new ResponseEntity<Response>(userServiceImp.validateUser(token), HttpStatus.OK);
	}
	
	// API for Login the user from Database.
	@PostMapping("/login")
	public ResponseEntity<Response> loginUser(@RequestBody LoginDto logindto) {

		return new ResponseEntity<Response>(userServiceImp.loginUser(logindto), HttpStatus.OK);
	}
	
	// API for recovering in case of forgotton password 
	@PostMapping("/forgotpassword")
	public String findEmail(@RequestBody  ForgotPasswordDto forgetPasswordDto) {

		userServiceImp.forgotPassword(forgetPasswordDto);
		return Messages.MAIL_SENT;
	}
	
	// API for setting new Password
	@PostMapping("/setpassword")
	public ResponseEntity<Response> setNewPassword(@RequestParam String token,
			@RequestBody ResetPasswordDto setpassworddto) {
		System.out.println("in controller");
		return new ResponseEntity<Response>(userServiceImp.setPassword(setpassworddto, token), HttpStatus.OK);
	}
	
	// API for finding a particular user
	@GetMapping("/finduser")
	public ResponseEntity<Response> findUser(@RequestBody String email, @RequestParam String token) {

		return new ResponseEntity<Response>(userServiceImp.findUser(token), HttpStatus.OK);
	}
	
	// API to show all Users
	@GetMapping("/showallusers")
	public Response showAllUsers(@RequestParam String token) {

		return new Response(Messages.OK,null,userServiceImp.showAllUsers(token));
	}
}