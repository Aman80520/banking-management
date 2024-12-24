package com.aman.demo.banking.serviceImpl;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
	
	private Long loanId;
	private String loanAccountNo;
	private double totalLoanAmount;
	private double restAmount;
	private Long accountNumber;

}
