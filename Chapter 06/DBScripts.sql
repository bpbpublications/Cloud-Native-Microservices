CREATE SCHEMA IF NOT EXISTS customer_enquiry
    AUTHORIZATION postgres;

COMMENT ON SCHEMA customer_enquiry
    IS 'Schema for Customer Enquiry service'
	
CREATE SCHEMA IF NOT EXISTS enquiry_broadcast
    AUTHORIZATION postgres;

COMMENT ON SCHEMA enquiry_broadcast
    IS 'Schema for Enquiry Broadcast service';


CREATE TABLE IF NOT EXISTS customer_enquiry."user"
(
    user_id serial NOT NULL,
    user_name character varying(50) NOT NULL,
    user_customer_id integer,
    created_date date NOT NULL DEFAULT CURRENT_DATE,
    created_by character varying(50) NOT NULL,
    CONSTRAINT user_pf PRIMARY KEY (user_id),
    CONSTRAINT user_uk1 UNIQUE (user_name),
	    CONSTRAINT user_fk2 FOREIGN KEY (created_by)
	        REFERENCES customer_enquiry."user" (user_name) MATCH SIMPLE
	        ON UPDATE NO ACTION
	        ON DELETE NO ACTION
	        NOT VALID
);

CREATE TABLE IF NOT EXISTS customer_enquiry.customer
(
    customer_id serial NOT NULL,
    customer_name character varying(50) NOT NULL,
    customer_gender character(1),
    customer_dob date,
    created_date date NOT NULL DEFAULT CURRENT_DATE,
    created_by character varying NOT NULL,
    CONSTRAINT customer_pk PRIMARY KEY (customer_id),
	    CONSTRAINT customer_fk1 FOREIGN KEY (created_by)
	        REFERENCES customer_enquiry."user" (user_name) MATCH SIMPLE
	        ON UPDATE NO ACTION
	        ON DELETE NO ACTION
	        NOT VALID
);


CREATE TABLE IF NOT EXISTS customer_enquiry.enquiry
(
    enquiry_id serial NOT NULL,
    enquiry_customer_id integer NOT NULL,
    enquiry_about character(5) NOT NULL,
    created_date date NOT NULL DEFAULT CURRENT_DATE,
    created_by character varying(50) NOT NULL,
    CONSTRAINT enquiry_pkey PRIMARY KEY (enquiry_id),
    CONSTRAINT enquiry_fk1 FOREIGN KEY (created_by)
	        REFERENCES customer_enquiry."user" (user_name) MATCH SIMPLE
	        ON UPDATE NO ACTION
	        ON DELETE NO ACTION
	        NOT VALID,
	    CONSTRAINT enquiry_fk2 FOREIGN KEY (enquiry_customer_id)
	        REFERENCES customer_enquiry.customer (customer_id) MATCH SIMPLE
	        ON UPDATE NO ACTION
	        ON DELETE NO ACTION
	        NOT VALID
);

ALTER TABLE IF EXISTS customer_enquiry."user"
    ADD CONSTRAINT user_fk1 FOREIGN KEY (user_customer_id)
    REFERENCES customer_enquiry.customer (customer_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


INSERT INTO customer_enquiry.user(
 user_name, created_by)
 VALUES ('system', 'system');

INSERT INTO customer_enquiry.customer(
 customer_name, customer_gender, customer_dob, created_by)
 VALUES ('John Paul', 'M', TO_DATE('19850101','YYYYMMDD'), 'system');
 
INSERT INTO customer_enquiry.customer(
 customer_name, customer_gender, customer_dob, created_by)
 VALUES ('Angela Ray', 'F', TO_DATE('19910202','YYYYMMDD'), 'system');


CREATE TABLE enquiry_broadcast.failed_request_s3
(
    failed_request_s3_id serial NOT NULL,
    failed_request_s3_customer_id integer NOT NULL,
    failed_request_s3_enquiry_about character(5) NOT NULL,
    failed_request_s3_date date NOT NULL,
    failed_request_s3_success_date date,
    created_date date NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY (failed_request_s3_id)
);