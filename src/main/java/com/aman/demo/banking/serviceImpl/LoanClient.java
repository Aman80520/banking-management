package com.aman.demo.banking.serviceImpl;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(url="http://localhost:8053",value="loan-client")
public interface LoanClient {

	@GetMapping("/loan/account/{accountNumber}")
	List<Loan> getLoanByAccId(@PathVariable Long accountNumber);
	
}
