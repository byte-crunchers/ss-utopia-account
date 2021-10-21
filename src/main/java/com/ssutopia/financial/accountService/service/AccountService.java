package com.ssutopia.financial.accountService.service;


import java.util.List;
import java.util.Optional;

import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.entity.CardPayment;
import com.ssutopia.financial.accountService.entity.UserAccount;

public interface AccountService {
    List<UserAccount> getAllAccounts();
    Accounts payLoan(Long originId, Float payment);
    CardPayment payCard(Long originId, Long destinationId, Float payment);
    Accounts updateAccount(UserAccount accountInfo);
    Optional<CardPayment> getCardPayment(Long id);
}
