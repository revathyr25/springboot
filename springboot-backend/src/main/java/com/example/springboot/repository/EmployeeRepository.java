package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
