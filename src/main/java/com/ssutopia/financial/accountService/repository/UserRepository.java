package com.ssutopia.financial.accountService.repository;

import com.ssutopia.financial.accountService.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
