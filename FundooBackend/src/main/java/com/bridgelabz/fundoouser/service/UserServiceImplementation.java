package com.bridgelabz.fundoouser.service;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoouser.configuration.PasswordConfiguration;
import com.bridgelabz.fundoouser.dto.ForgotPasswordDto;
import com.bridgelabz.fundoouser.dto.LoginDto;
import com.bridgelabz.fundoouser.dto.RegistrationDto;
import com.bridgelabz.fundoouser.dto.ResetPasswordDto;
import com.bridgelabz.fundoouser.exception.ForgotPasswordException;
import com.bridgelabz.fundoouser.exception.RegistrationExcepton;
import com.bridgelabz.fundoouser.exception.TokenException;
import com.bridgelabz.fundoouser.exception.ValidateUserException;
import com.bridgelabz.fundoouser.message.MessageUtility;
import com.bridgelabz.fundoouser.message.Messages;
import com.bridgelabz.fundoouser.model.User;
import com.bridgelabz.fundoouser.repository.UserRepository;
import com.bridgelabz.fundoouser.response.Response;
import com.bridgelabz.fundoouser.utility.TokenUtility;

/**********************************************************************************************************
 * @author :Pramila Tawari 
 * Purpose :Service Implementation Class for
 *         implementing actual Flow/Logic
 *
 *********************************************************************************************************/

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepository repository; // create object user repo

	// JavaMailSender class
	@Autowired
	private JavaMailSender javaMailSender;

	// PasswordEncoder Object
	@Autowired
	private PasswordEncoder passwordEncoder;

	// Token utility Object
	@Autowired
	private TokenUtility tokenutility;

	// ModelMapper for mapping from object to dto or vice versa
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordConfiguration passConfig;

	/**
	 * @return User Registration Mathod :- After the User Registration, OTP/ Token
	 *         in been sent to Users Mail For Verification Using JMS(Java Mail
	 *         Sender).
	 *
	 */
	public Response Register(RegistrationDto regdto) {

		User user = mapper.map(regdto, User.class); // Mapping new User data into the user Class
		System.out.println(user.getFirstname());
		if (repository.findAll().stream().anyMatch(i -> i.getEmail().equals(regdto.getEmail()))) // check user already
		{
			throw new RegistrationExcepton(Messages.EMAIL_ALREADY_REGISTERED);
		}
		user.setPassword(passwordEncoder.encode(regdto.getPassword()));
		System.out.println(user);
		user = repository.save(user); // Storing Users Data in Database
		if (user == null) {
			throw new RegistrationExcepton(Messages.INVALID_EMAIL);
		}

		String token = tokenutility.createToken(user.getEmail());
		System.out.println(token);

		javaMailSender.send(MessageUtility.verifyUserMail(regdto.getEmail(), token, Messages.REGISTRATION_MAIL_TEXT));

		return new Response(200, "User Registration Completed ", Messages.USER_ADDED_SUCCESSFULLY);
	}

	/**
	 * @return Validating the User :- It Checks if the Token is from valid mail Id
	 *         or Not. If token is valid, it sets the validation as 1.
	 *
	 */
	public Response validateUser(String token) {

		String email = tokenutility.getUserToken(token); // get user id from user token.
		if (email.isEmpty()) {
			throw new TokenException(Messages.INVALID_TOKEN);
		}

		User user = repository.findByEmail(email);
		if (user != null) { // if userid is found validate should be true
			user.setValidate(true);
			repository.save(user);
			return new Response(200, "email  ", Messages.EMAIL_VERFIY);
		} else {
			return new Response(200, "Email Not Verified", Messages.NOT_VERFIY_EMAIL);
		}
	}

	/**
	 * @return User Login Method :- 
	 * 			Login the Authenticated User
	 *
	 */
	public Response loginUser(LoginDto logindto) {
		User user = repository.findByEmail(logindto.getEmail()); // find email present or not
		System.out.println(user);
		if (user == null) {
			return new Response(200, "User Registrtion ", Messages.INVALID_EMAIL);

		}
		String token = tokenutility.createToken(user.getEmail());

		if (!user.isValidate()) {

			new ValidateUserException(Messages.LINK_NOT_ACTIVE);
		} else {
			if (user.getEmail().equals(logindto.getEmail())
					&& passConfig.encoder().matches(logindto.getPassword(), user.getPassword())) { // encode the user
				return new Response(200, Messages.LOGIN_SUCCESSFUL, token); // password
			}
		}
		return new Response(200, "User Registrtion ", Messages.LOGIN_UNSUCCESSFUL);
	}

	/**
	 * @return Forgot Passwrod Method :-
	 * 			In Case if Password is not remebering then we can 
	 * 			recover it by sending token to the email id.
	 *
	 */
	public Response forgotPassword(ForgotPasswordDto forgetPasswordDto) {
		User user = repository.findByEmail(forgetPasswordDto.getEmail()); // find by user email id

		System.out.println(user);
		if (user == null) { // if user email id it null response to user not register it
			throw new ForgotPasswordException(Messages.USER_NOT_EXISTING);

		} else {

			String token = tokenutility.createToken(user.getEmail());
			System.out.println(token);

			javaMailSender.send(
					MessageUtility.verifyUserMail(forgetPasswordDto.getEmail(), token, Messages.VERIFY_MAIL)); // send
																													// email
		}
		return new Response(400, "user", "user  email found");
	}

	/**
	 * @return Update User Method :-
	 * 			Updating the user account by new Information
	 *
	 */
	public String updateUserByEmail(User user, String email) {

		User updateUser = repository.findByEmail(email);
		updateUser = user;
		repository.save(updateUser);
		return Messages.USER_UPDATE_SUCCESSFULLY;
	}
	
	/**
	 * @return Set Password Method :-
	 * 			Changing the Password 
	 *
	 */
	public Response setPassword(ResetPasswordDto setPasswordDto, String token) {
		// System.out.println(token + "@@@@@@@@@@@@@@@@@@@@@@@@");
		String useremail = tokenutility.getUserToken(token);

		User updateUser = repository.findByEmail(useremail);
		if (setPasswordDto.getPassword().equals(setPasswordDto.getConfirmpassword())) { // check password or cfmpassword

			updateUser.setPassword(passwordEncoder.encode(setPasswordDto.getPassword())); // new password encode it

			updateUserByEmail(updateUser, useremail);
			return new Response(200, "Changed password", Messages.PASSWORD_CHANGED_SUCCESSFULLY);
		} else {
			return new Response(400, "Error In Changing The Password", Messages.PASSWORD_NOT_MATCHING);
		}
	}
	
	/**
	 * @return Find User :-
	 * 		   Particular user's data by the token
	 *
	 */
	public Response findUser(String token) {
		String email = tokenutility.getUserToken(token);
		if (email.isEmpty()) {
			throw new TokenException(Messages.INVALID_TOKEN);
		}
		return new Response(200, "User Registrtion ", repository.findByEmail(email));
	}
	
	/**
	 * @return Show All Users Method :-
	 * 			Showing the users's List
	 *
	 */
	public List<User> showAllUsers(String token) {
		System.out.println("check");
		return repository.findAll(); // show all user details in JPA
	}
}