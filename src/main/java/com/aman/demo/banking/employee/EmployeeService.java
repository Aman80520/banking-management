package com.aman.demo.banking.employee;

import java.util.List;

public interface EmployeeService {
	
	Employee createEmp(Employee employee);
	Employee getEmpById(Long empId);
	List<Employee> getAllEmp();
	boolean deleteEmpById(Long empId);
	Employee updateEmp(Long empId,Employee employee);
	List<Employee> salaryGreaterthan(double salary);
	

}
