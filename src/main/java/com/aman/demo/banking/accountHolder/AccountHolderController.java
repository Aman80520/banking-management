package com.aman.demo.banking.accountHolder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aman.demo.banking.serviceImpl.Loan;
import com.aman.demo.banking.serviceImpl.LoanClient;

@RestController
@RequestMapping("/user")
public class AccountHolderController {
	
	@Autowired
	AccountHolderService accountHolderService;
	
	@Autowired
	LoanClient loanClient;
	
	@PostMapping("/save")
	public ResponseEntity<AccountUser> createUser(@RequestBody AccountUser accountUser){
		AccountUser saveAccountUser = accountHolderService.saveAccountUser(accountUser);
		return ResponseEntity.ok(saveAccountUser);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<AccountUser>> getAllUser(){
		List<AccountUser> allAccountHolder = accountHolderService.getAllAccountHolder();
		return ResponseEntity.ok(allAccountHolder);
		
	}
	
	@GetMapping("/{accountId}")
	public ResponseEntity<AccountUser> getbyAccountid(@PathVariable Long accountId){
		AccountUser allAccountHolder = accountHolderService.getAccountById(accountId);
		
		if (allAccountHolder!=null) {
			return new ResponseEntity<>(allAccountHolder,HttpStatus.FOUND);	
		}else
		return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		
	}
	
	@GetMapping("/accountnumber/{accountNumber}")
	public ResponseEntity<List<AccountUser>> getbyAccountnumber(@PathVariable Long accountNumber){
		 List<AccountUser> loanByAccountnum = accountHolderService.getLoanByAccountnum(accountNumber, null);
		
		if (loanByAccountnum!=null) {
			return new ResponseEntity<>(loanByAccountnum,HttpStatus.FOUND);	
		}else
		return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		
	}
	
	@DeleteMapping("/{accountId}")
	public ResponseEntity<String> deletebyAccountid(@PathVariable Long accountId){
		
		 boolean deleteByAccountId = accountHolderService.deleteByAccountId(accountId);
		 
		 if (deleteByAccountId) {
			 return new ResponseEntity<>("AccountId is  deleted successfully",HttpStatus.OK);
		}
		return  new ResponseEntity<>("AccountId is not present in Database which you are trying to delete",HttpStatus.BAD_REQUEST);
		
		
	}

	@PutMapping("/{accountId}")
	public ResponseEntity<AccountUser> updateUserById(@PathVariable Long accountId, @RequestBody AccountUser accountUser){
		AccountUser userDetails = accountHolderService.updateAccountUserDetails(accountUser, accountId);
		
		if (userDetails!=null) {
			return new ResponseEntity<>(userDetails,HttpStatus.OK);	
		}else
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		
	}
	
	@PutMapping("/withdraw/{accountId}")
	public ResponseEntity<String> withdrawalMoney(@PathVariable Long accountId, @RequestBody AccountUser userDetails){
		 String withdrawlMoney = accountHolderService.withdrawlMoney(userDetails.getAvilableBalance(), accountId);
		 return ResponseEntity.ok(withdrawlMoney);
	}
	
	@PutMapping("/deposite/{accountId}")
	public ResponseEntity<String> depositeMoney(@PathVariable Long accountId, @RequestBody AccountUser userDetails){
		 String depositeMoney = accountHolderService.depositeMoney(userDetails.getAvilableBalance(), accountId);
		 return ResponseEntity.ok(depositeMoney);
	}
	
//	@GetMapping("/{accountNumber}")
//	public ResponseEntity<List<AccountUser>> getByAccountNumber(@PathVariable Long accountNumber){
//		List<AccountUser> allAccountHolder = accountHolderService.findByAccont(accountNumber);
//		
//		if (allAccountHolder!=null) {
//			return  new ResponseEntity<>(allAccountHolder,HttpStatus.FOUND);
//		}
//		return new ResponseEntity<>(allAccountHolder,HttpStatus.NOT_FOUND);
//		
//		
//		
//	}
	
	@GetMapping("/loan/{accountNumber}")
	public ResponseEntity<List<Loan>> getLoanByAccountNumber(@PathVariable Long accountNumber){
		List<Loan> allAccountHolder = loanClient.getLoanByAccId(accountNumber);
		return new ResponseEntity<>(allAccountHolder,HttpStatus.OK);
		
	
		
		
	}
	
	
	
	
	

}
