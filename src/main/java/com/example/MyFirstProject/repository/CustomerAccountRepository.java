package com.example.MyFirstProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MyFirstProject.models.BankEmployee;
import com.example.MyFirstProject.models.CustomerAccount;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount,Long>{

	Optional<CustomerAccount> findByAccountNo(long accNo);

}
