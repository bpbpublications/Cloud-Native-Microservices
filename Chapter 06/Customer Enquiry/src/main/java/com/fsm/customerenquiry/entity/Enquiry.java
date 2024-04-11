package com.fsm.customerenquiry.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The persistent class for the enquiry database table.
 * 
 */
@Entity
@NamedQuery(name = "Enquiry.findAll", query = "SELECT e FROM Enquiry e")
public class Enquiry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ENQUIRY_ENQUIRYID_GENERATOR", sequenceName = "ENQUIRY_ENQUIRY_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENQUIRY_ENQUIRYID_GENERATOR")
	@Column(name = "enquiry_id")
	private Integer enquiryId;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "enquiry_about")
	private String enquiryAbout;

	// bi-directional many-to-one association to Customer
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "enquiry_customer_id")
	private Customer customer;

	public Enquiry() {
	}

	public Integer getEnquiryId() {
		return this.enquiryId;
	}

	public void setEnquiryId(Integer enquiryId) {
		this.enquiryId = enquiryId;
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

	public String getEnquiryAbout() {
		return this.enquiryAbout;
	}

	public void setEnquiryAbout(String enquiryAbout) {
		this.enquiryAbout = enquiryAbout;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}