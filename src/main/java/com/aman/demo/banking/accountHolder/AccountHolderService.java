package com.aman.demo.banking.accountHolder;

import java.util.List;

import com.aman.demo.banking.serviceImpl.Loan;

public interface AccountHolderService {
	AccountUser saveAccountUser(AccountUser accountUser);
	AccountUser getAccountById(Long accountId);
	List<AccountUser> getLoanByAccountnum(Long accountnum,AccountUser accountUser);
	List<AccountUser> getAllAccountHolder();
	boolean deleteByAccountId(Long accountId);
	AccountUser updateAccountUserDetails(AccountUser accountUser,Long accountId);
	String withdrawlMoney(double withdrwanmoney,Long accountId);
	String depositeMoney(double withdrwanmoney,Long accountId);
	List<Loan> findByAccont(Long accountNumber);

}
