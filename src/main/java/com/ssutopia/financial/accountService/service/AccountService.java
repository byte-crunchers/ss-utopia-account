package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.entity.UserAccount;

import java.util.List;

public interface AccountService {
    List<UserAccount> getAllAccounts();
    Accounts updateBalance(Long id, Float payment);
}
