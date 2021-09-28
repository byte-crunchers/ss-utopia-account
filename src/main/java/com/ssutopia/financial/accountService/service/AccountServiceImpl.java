package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{
    private final AccountsRepository accountsRepository;

    @Override
    public List<Accounts> getAllAccounts() {
        return accountsRepository.findAll();
    }


}
