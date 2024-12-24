package com.aman.demo.banking.accountHolder;

import java.util.List;

import com.aman.demo.banking.serviceImpl.Loan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AccountUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;
	
	private String acccountHolderName;
	private Long accountNumber;
	private double avilableBalance;
	private AccountType accountType;
	private String address;
	
	transient private List<Loan> loan;

}
