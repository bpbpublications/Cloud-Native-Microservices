package com.fsm.enquirybroadcast.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The persistent class for the failed_request_s3 database table.
 * 
 */
@Entity
@Table(name = "failed_request_s3")
@NamedQuery(name = "FailedRequestS3.findAll", query = "SELECT f FROM FailedRequestS3 f")
public class FailedRequestS3 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "FAILED_REQUEST_S3_FAILEDREQUESTS3ID_GENERATOR", sequenceName = "FAILED_REQUEST_S3_FAILED_REQUEST_S3_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FAILED_REQUEST_S3_FAILEDREQUESTS3ID_GENERATOR")
	@Column(name = "failed_request_s3_id")
	private Integer failedRequestS3Id;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "failed_request_s3_customer_id")
	private Integer failedRequestS3CustomerId;

	@Temporal(TemporalType.DATE)
	@Column(name = "failed_request_s3_date")
	private Date failedRequestS3Date;

	@Column(name = "failed_request_s3_enquiry_about")
	private String failedRequestS3EnquiryAbout;

	@Temporal(TemporalType.DATE)
	@Column(name = "failed_request_s3_success_date")
	private Date failedRequestS3SuccessDate;

	public FailedRequestS3() {
	}

	public Integer getFailedRequestS3Id() {
		return this.failedRequestS3Id;
	}

	public void setFailedRequestS3Id(Integer failedRequestS3Id) {
		this.failedRequestS3Id = failedRequestS3Id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getFailedRequestS3CustomerId() {
		return this.failedRequestS3CustomerId;
	}

	public void setFailedRequestS3CustomerId(Integer failedRequestS3CustomerId) {
		this.failedRequestS3CustomerId = failedRequestS3CustomerId;
	}

	public Date getFailedRequestS3Date() {
		return this.failedRequestS3Date;
	}

	public void setFailedRequestS3Date(Date failedRequestS3Date) {
		this.failedRequestS3Date = failedRequestS3Date;
	}

	public String getFailedRequestS3EnquiryAbout() {
		return this.failedRequestS3EnquiryAbout;
	}

	public void setFailedRequestS3EnquiryAbout(String failedRequestS3EnquiryAbout) {
		this.failedRequestS3EnquiryAbout = failedRequestS3EnquiryAbout;
	}

	public Date getFailedRequestS3SuccessDate() {
		return this.failedRequestS3SuccessDate;
	}

	public void setFailedRequestS3SuccessDate(Date failedRequestS3SuccessDate) {
		this.failedRequestS3SuccessDate = failedRequestS3SuccessDate;
	}

}