package com.example.MyFirstProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MyFirstProject.models.BankEmployee;

@Repository
public interface BankEmployeeRepository extends JpaRepository<BankEmployee,Long>{

	Optional<BankEmployee> findByEmployeeNo(long id);

}
