package com.example.MyFirstProject.dtos;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.example.MyFirstProject.models.ApplicationForm;
import com.example.MyFirstProject.models.BankEmployee;

import lombok.Data;

@Data
public class KYCCreateReqDtos {
	
	private Long kycId;
	
	private Boolean isAprroved;

	private Boolean isVerified;
	
	ApplicationForm application;

	BankEmployee issuer;

}
