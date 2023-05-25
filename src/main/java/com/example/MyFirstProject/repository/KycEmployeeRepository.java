package com.example.MyFirstProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MyFirstProject.models.KycEmployee;

@Repository
public interface KycEmployeeRepository extends JpaRepository<KycEmployee,Long>{

}
