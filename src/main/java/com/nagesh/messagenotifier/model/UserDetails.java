package com.nagesh.messagenotifier.model;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagesh.messagenotifier.DB.UserRepository;
import com.nagesh.messagenotifier.entityClass.User;

@Service
public class UserDetails {

	@Autowired 
	OTPMethods otpm;
	
	
	public void createNewUser(String email,String source){
		String status="Y";
		if(source.equals("Email"))
			 status="N";
		User user=new User(email,source,otpm.getCurentTimeStamp(),status);
		saveUser(user);
	}
	
	@Autowired
	UserRepository repod;
	
	public void saveUser(User user){
		repod.save(user);
	}
	
	public User getUserDetails(String email){
	System.out.println("Not null");
		User user=repod.findByEmail(email);
		return user;		
	}
	public boolean ifUserAlreadyExists(String email){
		User user=getUserDetails(email);
		if(user==null) return false;
		return true;
	}
	public boolean ifUserVerified(String email){
		User user;
		user=getUserDetails(email);
		if(user==null)
			return false;
		if(user.getStatus().equals("Y"))
			return true;
		return false;
	}
	
	public boolean updateUserStatus(String email){
		User user=getUserDetails(email);
		user.setStatus("Y");
		saveUser(user);
		return true;
	}
}
