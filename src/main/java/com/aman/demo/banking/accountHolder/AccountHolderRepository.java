package com.aman.demo.banking.accountHolder;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AccountHolderRepository extends JpaRepository<AccountUser, Long> {
	
	List<AccountUser> findByAccountNumber(Long accountNumber);

}
