package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.dto.CreditLimitDto;
import com.ssutopia.financial.accountService.entity.*;

import java.util.List;

public interface CardService {
    List<DebitAccount> getDebitCards();
    List<CreditAccount> getCreditCards();

    Accounts disableCard(Long accountId);

    Cards requestNewCard(Long cardId);

    void increaseCreditLimit(CreditLimitDto creditLimitDto);
    Integer viewCreditLimit(Long id);

}
