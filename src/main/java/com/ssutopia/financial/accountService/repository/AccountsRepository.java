package com.ssutopia.financial.accountService.repository;


import com.ssutopia.financial.accountService.entity.Accounts;

import com.ssutopia.financial.accountService.entity.UserAccount;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts,Long> {
    @Query( "select new com.ssutopia.financial.accountService.entity.UserAccount" +
            "( a.id,a.users.first_name,a.users.last_name,a.accountTypes.id" +
            ",a.balance,a.debt_interest,a.accountTypes.savings_interest,a.accountTypes.annual_fee" +
            ",a.payment_due, a.due_date, a.credit_limit, c.card_num, a.confirmed, a.approved, a.active ) " +
            
            "from Accounts a, Users u, Cards c where a.users = u and c.accounts = a " +
            "order by a.id"
            )
    List<UserAccount> findAllAccounts();


//    @Query("select a.active from Accounts a where a.id = ?1")
//    boolean findAccountSuspendStatus(Long id);

//    @Modifying
//    @Query("update Accounts a SET a.active = :true where a.id = :id")
//    boolean UpdateAccountSuspendStatus(Long id);

}
