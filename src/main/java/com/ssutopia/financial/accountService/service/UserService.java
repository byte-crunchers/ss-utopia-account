package com.ssutopia.financial.accountService.service;

import java.util.List;

import com.ssutopia.financial.accountService.dto.UserInfoDto;
import com.ssutopia.financial.accountService.entity.Users;

public interface UserService {
    Users findUserByUsername(String name);
    
    List<UserInfoDto> getUserInfo(String username);
}
