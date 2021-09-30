package com.ssutopia.financial.accountService.repository;


import com.ssutopia.financial.accountService.entity.Cards;
import com.ssutopia.financial.accountService.entity.CreditAccount;
import com.ssutopia.financial.accountService.entity.DebitAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends CrudRepository<Cards,Long> {
    @Query(
            "select new com.ssutopia.financial.accountService.entity.CreditAccount" +
                    "( c.id,c.card_num, c.accounts.users.first_name,c.accounts.users.last_name," +
                    "c.accounts.balance,c.exp_date,c.accounts.limit,c.cvc1,c.cvc2,c.accounts.payment_due,c.accounts.due_date)" +
                    "from Cards c, Accounts a where c.accounts = a and a.due_date is not null"
    )
    List<CreditAccount> findAllCreditCard();

    @Query(
            "select new com.ssutopia.financial.accountService.entity.DebitAccount" +
                    "( c.id,c.card_num, c.accounts.users.first_name,c.accounts.users.last_name" +
                    ",c.accounts.balance," +
                    "c.pin,c.exp_date,c.accounts.accountTypes.savings_interest,c.accounts.accountTypes.annual_fee) " +
                    "from Cards c, Accounts a where c.accounts = a and a.due_date is null"
    )
    List<DebitAccount> findAllDebitCard();
}
