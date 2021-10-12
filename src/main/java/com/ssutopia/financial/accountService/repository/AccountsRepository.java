package com.ssutopia.financial.accountService.repository;


import com.ssutopia.financial.accountService.entity.Accounts;

import com.ssutopia.financial.accountService.entity.UserAccount;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts,Long> {
    @Query( "select new com.ssutopia.financial.accountService.entity.UserAccount" +
            "( a.id,a.users.first_name,a.users.last_name,a.accountTypes.id" +

            ",a.balance,a.debt_interest,a.accountTypes.savings_interest,a.accountTypes.annual_fee) " +
            "from Users u, Accounts a where a.users = u ")
    List<UserAccount> findAllAccounts();
}
