package com.ssutopia.financial.accountService.repository;


import com.ssutopia.financial.accountService.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {


}
