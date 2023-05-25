package com.example.MyFirstProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MyFirstProject.models.ApplicationForm;
import com.example.MyFirstProject.services.CreditCardService;

@RestController
public class CreditCardController {
	
	@Autowired
	private CreditCardService creditCardService;
	
	@PostMapping("/creditcards/requests")
	@PreAuthorize("hasAnyAuthority('EMP','CUST')")
	public ResponseEntity<?> newCCRequest(@RequestBody ApplicationForm applicationForm){
		return creditCardService.applyCreditCard(applicationForm);
	}
	
	@GetMapping("/creditcards/requests")
	@PreAuthorize("hasAnyAuthority('EMP','CUST')")
	public ResponseEntity<?> getCardApplications(){
		return creditCardService.getCardApplications();
	}
	
	@DeleteMapping("/creditcards/requests/{id}")
	@PreAuthorize("hasAuthority('EMP')")
	public ResponseEntity<?> deleteRequest(@PathVariable long id){
		return creditCardService.deleteApplications(id);
	}

}
