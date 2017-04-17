package com.nagesh.messagenotifier.entityClass;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="otpgen")
@NamedNativeQuery(name="OTP.findByEmail",query="SELECT * FROM otpgen WHERE gentime > current_date - interval '1' day and email=:email",resultClass=OTP.class)
public class OTP {

	//@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rowid")
	private int RowID;
	@Column(name="email")
	private String Email;
	@Column(name="otp")
	private String OTP;
	@Column(name="gentime")
	private Timestamp GenTime;
	
	public OTP(){}
	
	public OTP(String email, String oTP,Timestamp genTime) {
		super();
		Email = email;
		OTP = oTP;
		GenTime=genTime;
	}

	public int getRowID() {
		return RowID;
	}
	
	public void setRowID(int rowID) {
		RowID = rowID;
	}
	
	public String getEmail() {
		return Email;
	}
	@Column
	public void setEmail(String email) {
		Email = email;
	}
	public String getOTP() {
		return OTP;
	}
	@Column
	public void setOTP(String OTP) {
		this.OTP = OTP;
	}
	public Timestamp getGenTime() {
		return GenTime;
	}
	public void setGenTime(Timestamp genTime) {
		GenTime = genTime;
	}

	@Override
	public String toString() {
		return "OTP [RowID=" + RowID + ", Email=" + Email + ", OTP=" + OTP + ", GenTime=" + GenTime + "]";
	}
	
	
}
