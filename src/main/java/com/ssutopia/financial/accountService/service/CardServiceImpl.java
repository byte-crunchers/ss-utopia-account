package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.entity.Cards;
import com.ssutopia.financial.accountService.entity.CreditAccount;
import com.ssutopia.financial.accountService.entity.DebitAccount;
import com.ssutopia.financial.accountService.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService{
    private final CardsRepository cardsRepository;

    @Override
    public List<DebitAccount> getDebitCards() {
        return cardsRepository.findAllDebitCard();
    }

    @Override
    public List<CreditAccount> getCreditCards() {
        return cardsRepository.findAllCreditCard();
    }
}
