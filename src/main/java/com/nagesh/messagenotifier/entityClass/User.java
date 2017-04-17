package com.nagesh.messagenotifier.entityClass;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="userdetail")
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rowid")
	private int rowid;
	private String email;
	private String source;
	private Timestamp created_date;
	private String status;
	public int getRowid() {
		return rowid;
	}
	public void setRowid(int rowid) {
		this.rowid = rowid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Timestamp getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [rowid=" + rowid + ", email=" + email + ", source=" + source + ", created_date=" + created_date
				+ ", status=" + status + "]";
	}
	
	public User() {
		super();
	}
	public User(String email, String source, Timestamp created_date, String status) {
		super();
		this.email = email;
		this.source = source;
		this.created_date = created_date;
		this.status = status;
	}
	
	
}
