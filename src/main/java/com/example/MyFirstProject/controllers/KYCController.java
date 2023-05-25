package com.example.MyFirstProject.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.MyFirstProject.dtos.KYCCreateReqDtos;
import com.example.MyFirstProject.services.KYCService;

@RestController
public class KYCController {
	
	@Autowired
	private KYCService KYCService;
	
	public ResponseEntity<?> createKycProcess(KYCCreateReqDtos kYCCreateReqDtos){
		return null;
	}
	
	public ResponseEntity<?> updateKyc(HashMap<String,Boolean> data, long id){
		return null;
	}

}
