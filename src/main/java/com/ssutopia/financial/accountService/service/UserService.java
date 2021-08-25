package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.entity.User;

public interface UserService {
    User findUserByUsername(String name);
}
