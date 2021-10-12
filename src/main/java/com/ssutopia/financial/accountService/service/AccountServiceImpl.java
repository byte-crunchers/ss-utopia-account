package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.entity.UserAccount;
import com.ssutopia.financial.accountService.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{
    private final AccountsRepository accountsRepository;

    @Override
    public List<UserAccount> getAllAccounts() {
        return accountsRepository.findAllAccounts();
    }

    @Override
    public Accounts updateBalance(Long id, Float payment) {
    	Accounts a = accountsRepository.findById(id).orElse(null);
    	
    	//verify there are sufficient funds in the account
    	if(a.getBalance() > payment)
    	{
    		a.setBalance(a.getBalance() - payment);
    		return accountsRepository.save(a);
    	}
    	
    	return null;
    }

}
