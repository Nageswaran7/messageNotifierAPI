package com.nagesh.messagenotifier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagesh.messagenotifier.DB.OTPRepository;
import com.nagesh.messagenotifier.entityClass.OTP;
import com.nagesh.messagenotifier.model.OTPMethods;
import com.nagesh.messagenotifier.model.UserDetails;

@RestController
public class APIController {

	@Autowired
	OTPRepository repo;
	@Autowired
	OTPMethods otpm;
	@Autowired
	UserDetails user;
	//Added
	@RequestMapping(value="/signIn",method=RequestMethod.GET)
		public String signIn(@RequestParam("email") String email){
		System.out.println("Generated OTP - "+email);
		OTP otpDetails;
		try{
			if(user.ifUserVerified(email))return "VER"; 
		otpDetails=otpm.getStoredOtp(email);
		if(otpDetails==null){
			otpDetails = new OTP(email,otpm.random(),otpm.getCurentTimeStamp());
			otpm.saveOTPtable(otpDetails);
			if(!user.ifUserAlreadyExists(email))
			user.createNewUser(email,"Email");
			otpm.sendOTPMail(email);
			return "NEW";
		}
		otpDetails.setGenTime(otpm.getCurentTimeStamp());
		otpm.saveOTPtable(otpDetails);
		otpm.sendOTPMail(email);
		return "UPD";
		}catch(Exception e){
			e.printStackTrace();
			return "FAIL";
		}
	}
	@RequestMapping(value="/signIn/{source}",method=RequestMethod.GET)
	public String signIn(@RequestParam("email") String email,@PathVariable String source){
		try{
		if(!user.ifUserAlreadyExists(email)){
			user.createNewUser(email, source);
			return "NEW";
		}
		return "GO";
		}catch(Exception e){
			e.printStackTrace();
			return "FAIL";
		}
	}
	@RequestMapping(value="/validateOTP",method=RequestMethod.GET)
	 public String getOTP(@RequestParam("email") String email,@RequestParam("otp") String otp){
		try{
		if(otpm.validateOTP(email, otp)){
			user.updateUserStatus(email);
			return "GO";
		}
		return "NOT";
		}catch(Exception e){
			e.printStackTrace();
			return "FAIL";
		}
	 }	
}
