package com.cmpe273.dropbox.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cmpe273.dropbox.mappers.GenericResponse;
import com.cmpe273.dropbox.mappers.SignUpRequest;
import com.cmpe273.dropbox.mappers.SignUpResponse;
import com.cmpe273.dropbox.mappers.SigninResponse;
import com.cmpe273.dropbox.mappers.UserEducationInfoRequest;
import com.cmpe273.dropbox.mappers.UserEducationInfoResponse;
import com.cmpe273.dropbox.mappers.UserInterestInfoRequest;
import com.cmpe273.dropbox.mappers.UserInterestsInfoResponse;
import com.cmpe273.dropbox.mappers.UserPersonalInfoRequest;
import com.cmpe273.dropbox.mappers.UserPersonalInfoResponse;
import com.cmpe273.dropbox.services.UserService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(path = "signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> userSignUp(@RequestBody SignUpRequest signupRequest) throws IOException {
		ResponseEntity res = null;
		SignUpResponse signUpResponse = userService.userSignup(signupRequest);
		Path path = Paths.get("files\\"+signupRequest.getEmail());
		Files.createDirectories(path);
		res = new ResponseEntity(signUpResponse, HttpStatus.OK);
		return res;
	}

	@PostMapping(path = "signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> userSignin(@RequestBody SignUpRequest signupRequest) {
		ResponseEntity res = null;
		SigninResponse signinResponse = userService.userSignin(signupRequest);
		res = new ResponseEntity(signinResponse, HttpStatus.OK);
		return res;
	}

	@PostMapping(path = "userPersonalInfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> userPersonalInfo(
			@RequestBody UserPersonalInfoRequest pinfoRequest) {
		ResponseEntity res = null;
		UserPersonalInfoResponse userPinfoResponse = userService
				.userPersonalInfo(pinfoRequest);
		res = new ResponseEntity(userPinfoResponse, HttpStatus.OK);
		return res;
	}

	@PostMapping(path = "userEduInfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> userEducationInfo(
			@RequestBody UserEducationInfoRequest eduinfoRequest) {
		ResponseEntity res = null;
		UserEducationInfoResponse userEduinfoResponse = userService
				.userEducationInfo(eduinfoRequest);
		res = new ResponseEntity(userEduinfoResponse, HttpStatus.OK);
		return res;
	}
	
	@PostMapping(path = "userIntInfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> userInterestsInfo(
			@RequestBody UserInterestInfoRequest eduinfoRequest) {
		ResponseEntity res = null;
		UserInterestsInfoResponse userIntinfoResponse = userService
				.userInterestInfo(eduinfoRequest);
		res = new ResponseEntity(userIntinfoResponse, HttpStatus.OK);
		return res;
	}
	
	@PostMapping(path = "signout", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> userSignout() {
		ResponseEntity res = null;
		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setCode(200);
		genericResponse.setMsg("User signed out");
		res = new ResponseEntity(genericResponse, HttpStatus.OK);
		return res;
	}
	
}
