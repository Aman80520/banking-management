package com.aman.demo.banking.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aman.demo.banking.employee.EmploeeRepository;
import com.aman.demo.banking.employee.Employee;
import com.aman.demo.banking.employee.EmployeeService;
import com.aman.demo.banking.exceptins.ResourceNotFoundException;

@Service
public class EmpServiceImpl implements EmployeeService{
	
	@Autowired
	private EmploeeRepository emploeeRepository;

	@Override
	public Employee createEmp(Employee employee) {
		// TODO Auto-generated method stub
		return emploeeRepository.save(employee);
	}

	@Override
	public Employee getEmpById(Long empId) {
		Optional<Employee> byId = emploeeRepository.findById(empId);
		return byId.orElseThrow(() -> new ResourceNotFoundException("Employee ID is not present"+empId));
	}

	@Override
	public List<Employee> getAllEmp() {
		return emploeeRepository.findAll();
	}

	@Override
	public boolean deleteEmpById(Long empId) {
		Optional<Employee> byId = Optional.ofNullable(emploeeRepository.findById(empId).get());
		if (byId.isPresent()) {
			emploeeRepository.deleteById(empId);
			return true;
		}
		return false;
	}

	@Override
	public Employee updateEmp(Long empId, Employee employee) {
		Optional<Employee> byId = emploeeRepository.findById(empId);
		
		Employee empUpdate = byId.get();
		
		if (empUpdate!=null) {
			empUpdate.setEmpName(employee.getEmpName());
			empUpdate.setEmpRole(employee.getEmpRole());
			empUpdate.setSalary(employee.getSalary());
			empUpdate.setEmpAddress(employee.getEmpAddress());
			Employee saved = emploeeRepository.save(empUpdate);
			return saved;
		}
		return null;
	}

	@Override
	public List<Employee> salaryGreaterthan(double salary) {
		List<Employee> salarygeater = emploeeRepository.findBySalaryGreaterThan(salary);
		return salarygeater;
	}

}
