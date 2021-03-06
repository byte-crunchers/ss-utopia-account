package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.dto.CardStatusDto;
import com.ssutopia.financial.accountService.dto.CreditLimitDto;
import com.ssutopia.financial.accountService.entity.AccountTypes;
import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.entity.CardForm;
import com.ssutopia.financial.accountService.entity.Cards;
import com.ssutopia.financial.accountService.entity.Users;
import com.ssutopia.financial.accountService.entity.CreditAccount;
import com.ssutopia.financial.accountService.entity.DebitAccount;
import com.ssutopia.financial.accountService.exception.NoSuchCreditCardException;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService{
	@Autowired
	private final UserRepository userRepository;
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

	@Override
	public void increaseCreditLimit(CreditLimitDto creditLimitDto) {
		notNull(creditLimitDto.getCardNum());
		if(cardsRepository.existsById(creditLimitDto.getCardNum())){
             var account = cardsRepository.findAccountByCardNum(creditLimitDto.getCardNum());
			if(!account.getAccountTypes().getId().toLowerCase().contains("credit")){
				throw new NoSuchCreditCardException(creditLimitDto.getCardNum());
			}
             account.setCredit_limit(creditLimitDto.getCreditLimit());
             accountRepository.save(account);
		}else {
			throw new NoSuchCreditCardException(creditLimitDto.getCardNum());
		}
	}

	@Override
	public Integer viewCreditLimit(Long id) {
		if(cardsRepository.existsById(id)){
			var account = cardsRepository.findAccountByCardNum(id);
			if(!account.getAccountTypes().getId().toLowerCase().contains("credit")){
				throw new NoSuchCreditCardException(id);
			}
		}else{
			throw new NoSuchCreditCardException(id);
		}

		return cardsRepository.findCreditAccountLimit(id);
	}


	public List<CardStatusDto> getAllCardsByUserId(Long id) {
    	return cardsRepository.findCardsByUserId(id);
    }

    public List<CardStatusDto> getDebitCardsByUserId(Long id) {
    	return cardsRepository.findDebitCardsByUserId(id);
    }
    
    @Override
    public Accounts disableCard(Long accountId) {
    	try {
	    	Accounts a = accountRepository.findById(accountId).orElse(null);
			a.setActive(false);
			return accountRepository.save(a);
    	}
    	catch(Exception ex) {
    		System.out.println("Update account failed!");
    		ex.printStackTrace();
    	}
    	
    	return null;
    }

	@Override
	public Cards requestNewCard(Long cardId) {
		Cards cards = cardsRepository.findById(cardId).orElse(null);
		Cards newCard;
		  if(cards != null){
			 Accounts account = cards.getAccounts();
			 //delete old card
			 cardsRepository.deleteById(cardId);
			  // generate 16 digit card number
			  Long newCardId = (long) (2319L * Math.pow(10, 12) + random4Digits() * Math.pow(10, 8)
					  + random4Digits() * Math.pow(10, 4) + random4Digits());

			  newCard = Cards.builder()
					  .card_num(newCardId)
					  .accounts(account)
					  .exp_date(LocalDate.now())
					  .cvc1(random3Digits())
					  .cvc2(random3Digits())
					  .pin(random4Digits())
					  .build();
			  // create new credit card
			  cardsRepository.save(newCard);
			  System.out.println(cardsRepository.existsById(cardId));

		  }	else {
			  throw new NoSuchCreditCardException(cardId);
		  }

         return newCard;
	}

	private Random rand = new Random();

	// generate a new card when form is submitted
	public Cards createNewCard(CardForm form) {

		// generate 16 digit card number
		Long cardId = (long) (2319L * Math.pow(10, 12) + random4Digits() * Math.pow(10, 8)
				+ random4Digits() * Math.pow(10, 4) + random4Digits());

		// get the user, create an account, and a card
		Users user = userRepository.getById(form.getUserId());
		AccountTypes accountType = accountTypeRepository.getById(form.getCardType());


		//int min = 50;
		//int max = 20000;
		//int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
		
        var Accounts6 = Accounts.builder()
                .balance(0f)
                .accountTypes(accountType)
                .active(false)
                .approved(false)
                .confirmed(false)
                .due_date(new Date())
                .debt_interest(0.015f)
                .credit_limit(-3000)
                .payment_due(0f)
                .users(user)
                .build();
        accountRepository.save(Accounts6);
        
		// save new card instance
		Cards card = null;
		var Card6 = Cards.builder()
		        .card_num(cardId)
		        .accounts(Accounts6)
		        .exp_date(LocalDate.now())
		        .cvc1(random3Digits())
		        .cvc2(random3Digits())
		        .pin(random4Digits())
		        .build();
        card = Card6;

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
