package com.ssutopia.financial.accountService.repository;

import com.ssutopia.financial.accountService.entity.AccountTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountTypes,String> {
//    @Query("select u from Account_type u where u.account_name = ?1")
//    Optional<AccountTypes> findByAccountName(String account_name);
}
