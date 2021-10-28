package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.entity.UserAccount;
import com.ssutopia.financial.accountService.exception.AlreadySuspendedException;
import com.ssutopia.financial.accountService.exception.NoSuchAccountException;
import com.ssutopia.financial.accountService.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void deleteAccountById(Long id) {
        notNull(id);
        if(!accountsRepository.existsById(id)){
            throw new NoSuchAccountException(id);
        }
        accountsRepository.deleteById(id);

    }

    @Override
    public void suspendAccountById(boolean status,Long id) {
        notNull(id);

        if(!accountsRepository.existsById(id)){
            throw new NoSuchAccountException(id);
        }

        accountsRepository.findById(id).map(
                accounts -> {
                    if(accounts.isActive()){
                        accounts.setActive(status);
                        return accountsRepository.save(accounts);
                    }else {
                        throw new AlreadySuspendedException(id);
                    }
                });
}
//        if(!accountsRepository.findAccountSuspendStatus(id)){
//            throw new AlreadySuspendedException(id);
//        }else{
//            accountsRepository.UpdateAccountSuspendStatus(id);
//        }



    /**
     * Util method to check for null ID values.
     *
     * @param ids vararg ids to check.
     */
    private void notNull(Object... ids) {
        for (var i : ids) {
            if (i == null) {
                throw new IllegalArgumentException("Expected value but received null.");
            }
        }
    }

}
