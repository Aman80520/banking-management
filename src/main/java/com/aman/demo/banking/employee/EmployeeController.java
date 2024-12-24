package com.aman.demo.banking.employee;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
	this.employeeService = employeeService;
	}



	@PostMapping("/save")
	public ResponseEntity<Employee> createEmp(@RequestBody Employee employee){
		Employee emp = employeeService.createEmp(employee);
		return new ResponseEntity<Employee>(emp,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/delete/{empId}")
	public ResponseEntity<String> removeEmp(@PathVariable Long empId){
		boolean deleteEmpById = employeeService.deleteEmpById(empId);
		
		if (deleteEmpById) {
			return new ResponseEntity<>("Deleted Successfully",HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Employee Id is not present",HttpStatus.CREATED);
		
	}

	@GetMapping("/emp/{empId}")
	public ResponseEntity<Employee> getEmpId(@PathVariable Long empId){
		Employee employee = employeeService.getEmpById(empId);
		
		if (employee!=null) {
			return new ResponseEntity<>(employee,HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<Employee>> getAllEmp(){
		List<Employee> allEmp = employeeService.getAllEmp();
		 return new ResponseEntity<>(allEmp,HttpStatus.OK);
		
	}
	@PutMapping("/update/{empId}")
	public ResponseEntity<Employee> updateEmp(@PathVariable Long empId,@RequestBody Employee employee){
		
		Employee empById = employeeService.getEmpById(empId);
		if (empById!=null) {
			Employee updateEmp = employeeService.updateEmp(empId, employee);
			 return new ResponseEntity<>(updateEmp,HttpStatus.OK);
		}
		
		 return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		
	}
	@GetMapping("/empl")
	//@ResponseStatus(HttpStatus.OK)
	public  ResponseEntity< List<Employee>> greaterSalary(@RequestParam double salary){
		List<Employee> salaryGreaterthan = employeeService.salaryGreaterthan(salary);
		if (salaryGreaterthan.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}else
			return new ResponseEntity<List<Employee>>(salaryGreaterthan,HttpStatus.OK);
	//	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
	
	
	
}
