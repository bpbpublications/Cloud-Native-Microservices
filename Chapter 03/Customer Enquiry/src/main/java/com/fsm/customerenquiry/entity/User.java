package com.fsm.customerenquiry.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USER_USERID_GENERATOR", sequenceName = "USER_USER_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_USERID_GENERATOR")
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "user_customer_id")
	private Integer userCustomerId;

	@Column(name = "user_name")
	private String userName;

	public User() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUserCustomerId() {
		return this.userCustomerId;
	}

	public void setUserCustomerId(Integer userCustomerId) {
		this.userCustomerId = userCustomerId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}