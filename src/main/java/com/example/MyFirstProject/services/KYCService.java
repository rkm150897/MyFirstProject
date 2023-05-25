package com.example.MyFirstProject.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.MyFirstProject.dtos.KYCCreateReqDtos;
import com.example.MyFirstProject.repository.ApplicationFormRepository;
import com.example.MyFirstProject.repository.BankEmployeeRepository;
import com.example.MyFirstProject.repository.KycEmployeeRepository;

@Service
public class KYCService {
	
	@Autowired
	private ApplicationFormRepository applicationFormRepository;
	
	@Autowired
	private BankEmployeeRepository bankEmployeeRepository;
	
	@Autowired
	private KycEmployeeRepository kycEmployeeRepository;
	
	public ResponseEntity<?> createKycProcess(KYCCreateReqDtos kYCCreateReqDtos){
		return null;
	}
	
	public ResponseEntity<?> updateKyc(HashMap<String,Boolean> data, long id){
		return null;
	}

}
