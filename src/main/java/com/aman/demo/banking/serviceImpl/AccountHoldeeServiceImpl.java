package com.aman.demo.banking.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aman.demo.banking.accountHolder.AccountHolderRepository;
import com.aman.demo.banking.accountHolder.AccountHolderService;
import com.aman.demo.banking.accountHolder.AccountUser;
import com.aman.demo.banking.exceptins.ResourceNotFoundException;



@Service
public class AccountHoldeeServiceImpl implements AccountHolderService{
	
	
	private AccountHolderRepository accountHolderRepository;
	@Autowired
	private LoanClient loanclient;
	
	public AccountHoldeeServiceImpl(AccountHolderRepository accountHolderRepository) {
		this.accountHolderRepository = accountHolderRepository;
		//this.loanclient=loanclient;
	}

	@Override
	public AccountUser saveAccountUser(AccountUser accountUser) {
		AccountUser save = accountHolderRepository.save(accountUser);
		return save;
	}

	@Override
	public AccountUser getAccountById(Long accountId) {
		Optional<AccountUser> byId = Optional.ofNullable(accountHolderRepository.findById(accountId).orElseThrow(()->new ResourceNotFoundException("Not avilable "+accountId)));
		
		AccountUser accountUser = byId.get();
		accountUser.setLoan(loanclient.getLoanByAccId(accountUser.getAccountNumber()));
		
		return accountUser;
		//return byId.orElseThrow(()->new ResourceNotFoundException("the accountid is not present in the database"+accountId));
	}

	@Override
	public List<AccountUser> getAllAccountHolder() {
		List<AccountUser> getallAccHolder = accountHolderRepository.findAll();
		
		List<AccountUser> collect = getallAccHolder.stream().map(accountuser ->
		{accountuser.setLoan(loanclient.getLoanByAccId(accountuser.getAccountNumber()));return accountuser;}).collect(Collectors.toList());
		return collect;
	}
	
//public List<College> getCollege() {
//		
//		List<College> allfind = collegeRepository.findAll();
//		List<College> newAccountList= allfind.stream().map(college->{college.setReview(reClient.getreviewByid(college.getId()));
//		return college;
//	}).collect(Collectors.toList());
//		return newAccountList;
//		
//	}
	
	
	
	

	@Override
	public boolean deleteByAccountId(Long accountId) {
		
		Optional<AccountUser> findbyId = accountHolderRepository.findById(accountId);
		if (findbyId!=null) {
			accountHolderRepository.deleteById(accountId);
			return true;
		}
		return false;
	}

	@Override
	public AccountUser updateAccountUserDetails(AccountUser accountUser, Long accountId) {
		Optional<AccountUser> findbyaccId = accountHolderRepository.findById(accountId);
		AccountUser accountUser2 = findbyaccId.get();
		accountUser2.setAcccountHolderName(accountUser.getAcccountHolderName());
		accountUser2.setAccountNumber(accountUser.getAccountNumber());
		accountUser2.setAccountType(accountUser.getAccountType());
		accountUser2.setAddress(accountUser.getAddress());
		accountUser2.setAvilableBalance(accountUser.getAvilableBalance());
		AccountUser updateduser = accountHolderRepository.save(accountUser2);
		
		return updateduser;
	}

	@Override
	public String withdrawlMoney(double withdrwanmoney, Long accountId) {
		AccountUser accountUser=  accountHolderRepository.findById(accountId).
		orElseThrow(() -> new ResourceNotFoundException("user is not present with this accountid "+accountId));
		if (accountUser.getAvilableBalance()>=withdrwanmoney) {
			accountUser.setAvilableBalance((accountUser.getAvilableBalance()-withdrwanmoney));
			AccountUser updatedUser = accountHolderRepository.save(accountUser);
			return updatedUser.toString();
		}
		return "Insufficient Balance";
	}

	@Override
	public String depositeMoney(double deposite, Long accountId) {
		AccountUser accountUser=  accountHolderRepository.findById(accountId).
				orElseThrow(() -> new ResourceNotFoundException("user is not present with this accountid "+accountId));
					accountUser.setAvilableBalance((accountUser.getAvilableBalance()+deposite));
					AccountUser updatedUser = accountHolderRepository.save(accountUser);
		return updatedUser.toString();
				
	}

	@Override
	public List<Loan> findByAccont(Long accountNumber) {
		List<Loan> loanByAccId = loanclient.getLoanByAccId(accountNumber);
		
		return loanByAccId;
	}

	@Override
	public List<AccountUser>getLoanByAccountnum(Long accountnum,AccountUser accountUser) {
		List<AccountUser> byAccountNumber = accountHolderRepository.findByAccountNumber(accountnum);
		
//		accountUser.getAccountNumber();
//		((AccountUser) byAccountNumber).setLoan(loanclient.getLoanByAccId(accountUser.getAccountNumber()));
		return byAccountNumber;
	}
//	@Override
//	public List<AccountUser> findByAccont(Long accountNumber) {
//		List<AccountUser> byAccountNumber = accountHolderRepository.findByAccountNumber(accountNumber);
//		return byAccountNumber;
//	}

	

}
