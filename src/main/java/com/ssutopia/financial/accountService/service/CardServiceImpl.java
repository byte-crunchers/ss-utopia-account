package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.dto.CardStatusDto;
import com.ssutopia.financial.accountService.entity.AccountTypes;
import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.entity.CardForm;
import com.ssutopia.financial.accountService.entity.Cards;
import com.ssutopia.financial.accountService.entity.Users;
import com.ssutopia.financial.accountService.entity.CreditAccount;
import com.ssutopia.financial.accountService.entity.DebitAccount;
import com.ssutopia.financial.accountService.repository.AccountTypeRepository;
import com.ssutopia.financial.accountService.repository.AccountsRepository;
import com.ssutopia.financial.accountService.repository.CardsRepository;
import com.ssutopia.financial.accountService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService{
	@Autowired
	private UserRepository userRepository;
    private final CardsRepository cardsRepository;
    private final AccountsRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    
    String expDate = "11/25"; // for example
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");

    @Override
    public List<DebitAccount> getDebitCards() {
        return cardsRepository.findAllDebitCard();
    }

    @Override
    public List<CreditAccount> getCreditCards() {
        return cardsRepository.findAllCreditCard();
    }
    
    public List<CardStatusDto> getAllCardsByUserId(Long id) {
    	return cardsRepository.findCardsByUserId(id);
    }

    public List<CardStatusDto> getDebitCardsByUserId(Long id) {
    	return cardsRepository.findDebitCardsByUserId(id);
    }

	private Random rand = new Random();

	// generate a new card when form is submitted
	public Cards createNewCard(CardForm form) {

		// generate 16 digit card number
		Long cardId = (long) (2319L * Math.pow(10, 12) + random4Digits() * Math.pow(10, 8)
				+ random4Digits() * Math.pow(10, 4) + random4Digits());

		// TODO - get the user, create an account, and a card
		Users user = userRepository.getById(form.getUserId());
		AccountTypes accountType = accountTypeRepository.getById(form.getCardType());
		
        var Accounts6 = Accounts.builder()
                .balance(0f)
                .accountTypes(accountType)
                .active(false)
                .approved(false)
                .confirmed(false)
                .due_date(new Date())
                .debt_interest(0.015f)
                .limit(3000)
                .payment_due(0f)
                .users(user)
                .build();
        accountRepository.save(Accounts6);
        
		// save new card instance
		Cards card = null;
        try {
			var Card6 = Cards.builder()
			        .card_num(cardId)
			        .accounts(Accounts6)
			        .exp_date(simpleDateFormat.parse(expDate))
			        .cvc1(random3Digits())
			        .cvc2(random3Digits())
			        .pin(random4Digits())
			        .build();
	        card = Card6;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return cardsRepository.save(card);
	}

	// get 1 card by card number
	public Optional<Cards> getCard(Long id) {
		return cardsRepository.findById(id);
	}

	private int random4Digits() {
		return 1000 + rand.nextInt(9000); // range 1000 - 9999;
	}

	private int random3Digits() {
		return 100 + rand.nextInt(900); // range 100 - 999;
	}
}
