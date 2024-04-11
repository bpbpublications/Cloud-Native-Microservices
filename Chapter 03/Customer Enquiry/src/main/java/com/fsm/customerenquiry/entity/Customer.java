package com.fsm.customerenquiry.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CUSTOMER_CUSTOMERID_GENERATOR", sequenceName = "CUSTOMER_CUSTOMER_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_CUSTOMERID_GENERATOR")
	@Column(name = "customer_id")
	private Integer customerId;

	@Column(name = "created_by")
	private String createdBy;

	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	private Date createdDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "customer_dob")
	private Date customerDob;

	@Column(name = "customer_gender")
	private String customerGender;

	@Column(name = "customer_name")
	private String customerName;

	// bi-directional many-to-one association to Enquiry
	@OneToMany(mappedBy = "customer")
	private List<Enquiry> enquiries;

	public Customer() {
	}

	public Customer(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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

	public Date getCustomerDob() {
		return this.customerDob;
	}

	public void setCustomerDob(Date customerDob) {
		this.customerDob = customerDob;
	}

	public String getCustomerGender() {
		return this.customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<Enquiry> getEnquiries() {
		return this.enquiries;
	}

	public void setEnquiries(List<Enquiry> enquiries) {
		this.enquiries = enquiries;
	}

	public Enquiry addEnquiry(Enquiry enquiry) {
		getEnquiries().add(enquiry);
		enquiry.setCustomer(this);

		return enquiry;
	}

	public Enquiry removeEnquiry(Enquiry enquiry) {
		getEnquiries().remove(enquiry);
		enquiry.setCustomer(null);

		return enquiry;
	}

}