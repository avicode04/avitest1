package com.stackroute.controller;

import com.stackroute.config.JwtTokenUtil;
import com.stackroute.domain.JwtRequest;
import com.stackroute.domain.JwtResponse;
import com.stackroute.domain.JwtUser;
import com.stackroute.domain.UserDTO;
import com.stackroute.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthUserService userDetailsService;

   /*API endpoint for authenticating the log-in of a registered user.*/
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getEmailId(), authenticationRequest.getPassword());

		final JwtUser userDetails = (JwtUser) userDetailsService
				.loadUserByUsername(authenticationRequest.getEmailId());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	/*Api endpoint for registering a new user to database.
	* only used in testing.*/
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO user) throws Exception {
//		return ResponseEntity.ok(userDetailsService.save(user));
		try {
			user.setLoginType("general");
			userDetailsService.save(user);
			return ResponseEntity.ok(new JwtResponse("User Saved"));
		}catch (Exception e){
			return ResponseEntity.ok(new JwtResponse("UserEmail already Exists"));
		}
	}
    /*Api for logging in a user with social-media log-in*/
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		try{
			if(user.getLoginType().equalsIgnoreCase("google") || user.getLoginType().equalsIgnoreCase("facebook")){
				try {
					userDetailsService.save(user);
				}catch (Exception e){
					System.out.println("No need to save");
				}
			}
			final JwtUser userDetails = (JwtUser) userDetailsService
					.loadUserByUsername(user.getEmailId());
			System.out.println(userDetails);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if(!encoder.matches(user.getPassword(),userDetails.getPassword())){
				return ResponseEntity.ok(new JwtResponse("Login failed wrong Password"));
			}
			final String token = jwtTokenUtil.generateToken(userDetails);
			return ResponseEntity.ok(new JwtResponse(token));
		}catch (Exception e){
			return ResponseEntity.ok(new JwtResponse("Login failed check Email"));
		}

	}

	//Function to authenticate a user when the user provides Email and password.
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}