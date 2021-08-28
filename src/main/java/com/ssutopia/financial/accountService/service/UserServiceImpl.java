package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.entity.User;
import com.ssutopia.financial.accountService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User findUserByUsername(String name) {
        return userRepository.findByUsername(name);
    }
}
