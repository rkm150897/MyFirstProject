package com.example.MyFirstProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.MyFirstProject.models.BankEmployee;
import com.example.MyFirstProject.models.CustomerAccount;
import com.example.MyFirstProject.repository.BankEmployeeRepository;
import com.example.MyFirstProject.repository.CustomerAccountRepository;
import com.example.MyFirstProject.repository.KycEmployeeRepository;

@Service
public class AuthService implements UserDetailsService {
	
	@Autowired
	private BankEmployeeRepository bankEmployeeRepository;
	
	@Autowired
	private CustomerAccountRepository customerAccountRepository;
	
	@Autowired
	private KycEmployeeRepository kycEmployeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<BankEmployee> bankEmployee = bankEmployeeRepository.findByEmployeeNo(Long.valueOf(username)).stream().findFirst();
		if (bankEmployee.isPresent()) {
			return getEmployeeWithNo(Long.valueOf(username));
		} else if(customerAccountRepository.findByAccountNo(Long.valueOf(username)).stream().findFirst().isPresent()){
			return getCustomerWithNo(Long.valueOf(username));
		}else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	public UserDetails getEmployeeWithNo(long id) throws UsernameNotFoundException {
		BankEmployee bankEmployee = bankEmployeeRepository.findByEmployeeNo(id)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + id));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String testPasswordEncoded = passwordEncoder.encode(bankEmployee.getPswd());
        bankEmployee.setPswd(testPasswordEncoded);
		return buildUserForAuthentication(bankEmployee, buildUserAuthority("EMP"));
	}
	
	public UserDetails getCustomerWithNo(long accNo) throws UsernameNotFoundException {
		CustomerAccount customerAccount = customerAccountRepository.findByAccountNo(accNo)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + accNo));
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String testPasswordEncoded = passwordEncoder.encode(customerAccount.getPswd());
        customerAccount.setPswd(testPasswordEncoded);
		return buildUserForAuthentication(customerAccount, buildUserAuthority("CUST"));
	}
	
	private User buildUserForAuthentication(BankEmployee bankEmployee,List<GrantedAuthority> authorities) {
		return new User(bankEmployee.getEmployeeNo().toString(), bankEmployee.getPswd(),
				authorities);
	}
	
	private User buildUserForAuthentication(CustomerAccount customer,List<GrantedAuthority> authorities) {
		return new User(customer.getAccountNo().toString(), customer.getPswd(),
				authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(String userRole) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(userRole));
		return authorities;
	}

}
