package com.aman.demo.banking.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmploeeRepository extends JpaRepository<Employee, Long>{
	
	List<Employee> findBySalaryGreaterThan(double salary);
	

}
