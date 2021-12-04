package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.entity.CardPayment;
import com.ssutopia.financial.accountService.entity.UserAccount;
import com.ssutopia.financial.accountService.exception.AlreadySuspendedException;
import com.ssutopia.financial.accountService.exception.NoSuchAccountException;
import com.ssutopia.financial.accountService.repository.AccountsRepository;
import com.ssutopia.financial.accountService.repository.CardPaymentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
	private final AccountsRepository accountsRepository;
	private final CardPaymentRepository cardPaymentRepository;

	@Override
	public List<UserAccount> getAllAccounts() {
		return accountsRepository.findAllAccounts();
	}

	@Transactional
	@Override
	public Accounts payLoan(Long originId, Float payment) {
		Accounts a = accountsRepository.findById(originId).orElse(null);

		// verify there are sufficient funds in the account
		if (a.getBalance() > payment) {
			a.setBalance(a.getBalance() - payment);
			return accountsRepository.save(a);
		}

		return null;
	}

	@Transactional
	@Override
	public CardPayment payCard(Long originId, Long destinationId, Float amount) {
		try {
			Accounts originAccount = accountsRepository.findById(originId).orElse(null);

			// verify there are sufficient funds in the account
			if (originAccount.getBalance() > amount) {
				// update checking account balance
				originAccount.setBalance(originAccount.getBalance() - amount);				
				accountsRepository.save(originAccount);

				// update card balalnce
				Accounts destinationAccount = accountsRepository.findById(destinationId).orElse(null);
				destinationAccount.setBalance(destinationAccount.getBalance() - amount);
				if(destinationAccount.getBalance() < 0.01)
					destinationAccount.setBalance(0f);
				
				destinationAccount.setPayment_due(destinationAccount.getPayment_due() - amount);
				if(destinationAccount.getPayment_due() < 0.01)
					destinationAccount.setPayment_due(0f);
				
				accountsRepository.save(destinationAccount);

				// create a new payment entity (stored in 'transactions' table in db)
				CardPayment payment = CardPayment.builder()
						.originAccount(originAccount)
						.destinationAccount(destinationAccount)
						.transfer_value(amount)
						.time_stamp(LocalDateTime.now())
						.build();
								
				return cardPaymentRepository.save(payment);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	// update account fields, submitted from 'admin edit account details' page
	@Override
	public Accounts updateAccount(UserAccount dto) {

		try {
			Accounts a = accountsRepository.findById(dto.getId()).orElse(null);

			a.setBalance(dto.getBalance());
//			a.setLimit(dto.getCredit_limit());
			a.setDebt_interest(dto.getDebt_interest());
			a.setPayment_due(dto.getPayment_due());
			a.setDue_date(dto.getDue_date());
			a.setConfirmed(dto.getConfirmed());
			a.setApproved(dto.getApproved());
			a.setActive(dto.getActive());

			return accountsRepository.save(a);
		} catch (Exception ex) {
			System.out.println("Update account failed!");
			ex.printStackTrace();
		}

		return null;
	}


	// get 1 card payment by ID
	@Override
	public Optional<CardPayment> getCardPayment(Long id) {
		return cardPaymentRepository.findById(id);
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
                    if(accounts.getActive()){
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
