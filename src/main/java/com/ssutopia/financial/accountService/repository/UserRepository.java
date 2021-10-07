package com.ssutopia.financial.accountService.repository;

import com.ssutopia.financial.accountService.dto.UserInfoDto;
import com.ssutopia.financial.accountService.entity.Users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public  interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    
    @Query(
            "select new com.ssutopia.financial.accountService.dto.UserInfoDto" +
                    "( u.id, u.username, u.first_name, u.last_name, u.email, u.address, u.city, u.state, u.zip, u.phone ) " +
                    "from Users u where u.username = ?1"
    )
    List<UserInfoDto> getUserInfoDto(String username);

}
