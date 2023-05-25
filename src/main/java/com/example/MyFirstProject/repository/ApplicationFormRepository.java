package com.example.MyFirstProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MyFirstProject.models.ApplicationForm;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm,Long>{

}
