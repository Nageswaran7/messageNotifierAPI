package com.nagesh.messagenotifier.model;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagesh.messagenotifier.DB.OTPRepository;
import com.nagesh.messagenotifier.entityClass.OTP;
import com.nagesh.messagenotifier.mail.EmailUtil;

@Service
public class OTPMethods {

	@Autowired
	OTPRepository repo;
	@Autowired
	UserDetails repou;
	 public  String random() {
		 	int size=4;
	        StringBuilder generatedToken = new StringBuilder();
	        try {
	            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
	            // Generate 20 integers 0..20
	            for (int i = 0; i < size; i++) {
	                generatedToken.append(number.nextInt(9));
	            }
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return generatedToken.toString();
	    }
	 
	 public  java.sql.Timestamp getCurentTimeStamp(){
		 Calendar calendar = Calendar.getInstance();
		 java.util.Date now = calendar.getTime();
		 java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		 return currentTimestamp;
	 }
	 
	 public OTP	getStoredOtp(String email){
			List<OTP> otp=repo.findByEmail(email);
			if(otp.size()==0)
				return null;
			return otp.get(0);
		}
	 
	 public boolean ifvalidOTPPresent(String email){
		 
		 return true;
	 }
	 public boolean validateOTP(String email,String otp){
		 OTP Storedotp=getStoredOtp(email);
		 if(Storedotp==null)
			 return false;
		 if(Storedotp.getOTP().equals(otp)){
			 return true;
		 }
			 
		 return false;
	 }
	 public void saveOTPtable(OTP otp){
		 repo.save(otp);
	 }
	 
	 public boolean sendOTPMail(String email){
		 Timestamp time;
		 OTP Storedotp=getStoredOtp(email);	 
		 if(Storedotp==null)
				return false;
		 time=new Timestamp(Storedotp.getGenTime().getTime()+(60 * 60 * 1000));
		return  EmailUtil.send(email, "Your OTP for Message Notifier", "Your OTP is "+Storedotp.getOTP()+". Your OTP is valid till "+time);
		
	 }

}
