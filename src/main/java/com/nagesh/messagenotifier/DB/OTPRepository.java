package com.nagesh.messagenotifier.DB;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nagesh.messagenotifier.entityClass.OTP;

public interface OTPRepository extends CrudRepository<OTP, Integer>
{
	 //  @Query("SELECT otp from otpgen where gentime > current_date - interval '1' day and email=:email")
	//	@Query("select otp from otpgen where gentime> :date and email=:email")
	   	@Query(nativeQuery=true)
		public List<OTP> findByEmail(@Param("email")String email);
}
