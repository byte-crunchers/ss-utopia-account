package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.entity.Users;

public interface UserService {
    Users findUserByUsername(String name);
}
