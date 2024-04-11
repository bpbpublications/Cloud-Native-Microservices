package com.fsm.enquirybroadcast.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsm.enquirybroadcast.entity.FailedRequestS3;

@Repository
public interface FailedRequestS3Repository extends JpaRepository<FailedRequestS3, Integer> {

}
