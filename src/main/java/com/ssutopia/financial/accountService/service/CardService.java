package com.ssutopia.financial.accountService.service;


import com.ssutopia.financial.accountService.dto.CreditLimitDto;
import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.entity.CreditAccount;
import com.ssutopia.financial.accountService.entity.DebitAccount;

import java.util.List;

public interface CardService {
    List<DebitAccount> getDebitCards();
    List<CreditAccount> getCreditCards();
    void increaseCreditLimit(CreditLimitDto creditLimitDto);
    int viewCreditLimit(Long id);
}
