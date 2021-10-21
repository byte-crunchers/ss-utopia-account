package com.ssutopia.financial.accountService.service;


import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.entity.CreditAccount;
import com.ssutopia.financial.accountService.entity.DebitAccount;
import com.ssutopia.financial.accountService.entity.UserAccount;

import java.util.List;

public interface CardService {
    List<DebitAccount> getDebitCards();
    List<CreditAccount> getCreditCards();
    Accounts disableCard(Long accountId);
}
