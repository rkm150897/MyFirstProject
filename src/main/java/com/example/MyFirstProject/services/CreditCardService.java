package com.example.MyFirstProject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.MyFirstProject.models.ApplicationForm;
import com.example.MyFirstProject.models.CustomerAccount;
import com.example.MyFirstProject.models.KycEmployee;
import com.example.MyFirstProject.repository.ApplicationFormRepository;
import com.example.MyFirstProject.repository.CustomerAccountRepository;
import com.example.MyFirstProject.repository.KycEmployeeRepository;

@Service
public class CreditCardService {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private ApplicationFormRepository applicationFormRepository;
	
	@Autowired
	private CustomerAccountRepository customerAccountRepository;
	
	@Autowired
	private KycEmployeeRepository kycEmployeeRepository;
	
	public ResponseEntity<ApplicationForm> applyCreditCard(ApplicationForm applicationForm){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<CustomerAccount> customerAccount= customerAccountRepository.findByAccountNo(Long.valueOf(authentication.getName())).stream().findFirst();
		applicationForm.setCustomerAccount(customerAccount.get());
		applicationFormRepository.save(applicationForm);
		/*
		 * KycEmployee kycEmployee = new KycEmployee();
		 * kycEmployee.setApplication(applicationForm);
		 * kycEmployeeRepository.save(kycEmployee);
		 */
		return ResponseEntity.ok(applicationForm);
	}
	
	public ResponseEntity<List<ApplicationForm>> getCardApplications(){
		List<ApplicationForm> applicationForm1 = applicationFormRepository.findAll();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<CustomerAccount> customerAccount= customerAccountRepository.findByAccountNo(Long.valueOf(authentication.getName())).stream().findFirst();
		if(authentication.getAuthorities().equals(new SimpleGrantedAuthority("CUST"))) {
			if(customerAccount.isPresent()) {
				applicationForm1.stream().forEach(c -> c.setCustomerAccount(customerAccount.get()));
			}
			return ResponseEntity.ok(applicationForm1);
		}else {
			return ResponseEntity.ok(applicationForm1); 
		}
		
	}
	
	public ResponseEntity<?> deleteApplications(long id){
		Boolean flag = true;
		Optional<KycEmployee> kycEmployee = kycEmployeeRepository.findAll().stream().filter(k -> k.getApplication().getApplicationId()==id).findFirst();
		if(kycEmployee.isPresent() && kycEmployee.get().getIsAprroved()==true) {
			return ResponseEntity.ok("Already approved");
		}else {
			applicationFormRepository.deleteById(id);
		}
		return ResponseEntity.ok("");
	}

}
