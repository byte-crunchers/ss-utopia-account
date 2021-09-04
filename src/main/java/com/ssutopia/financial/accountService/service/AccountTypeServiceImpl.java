package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.dto.AccountTypeDto;
import com.ssutopia.financial.accountService.entity.AccountType;
import com.ssutopia.financial.accountService.exception.DuplicateAccountNameException;
import com.ssutopia.financial.accountService.exception.NoSuchAccountTypeException;
import com.ssutopia.financial.accountService.repository.AccountTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    private final AccountTypeRepository account_typeRepository;
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    @Transactional
    public AccountType createNewAccount_type(AccountTypeDto account_typeDto) {
        validateDto(account_typeDto);
        log.debug("Create new account type=" + account_typeDto.getAccountName());
        account_typeRepository.findByAccountName(account_typeDto.getAccountName()).
                ifPresent(account_type -> {
                    throw new DuplicateAccountNameException(account_typeDto.getAccountName());
                });
        var account_type = AccountType.builder()
                .accountName(account_typeDto.getAccountName())
                .apy(account_typeDto.getApy())
                .contributionLimits(account_typeDto.getContributionLimits())
                .withdrawalAgeLimits(account_typeDto.getWithdrawalAgeLimits())
                .fee(account_typeDto.getFee())
                .accounttype(account_typeDto.getAccounttype())
                .withdrawalLimits(account_typeDto.getWithdrawalLimits())
                .build();
        account_type = account_typeRepository.save(account_type);
        return account_type;
    }

    @Override
    public AccountType getAccountTypeById(Long id) {
        return account_typeRepository.findById(id)
                .orElseThrow(()->new NoSuchAccountTypeException(id));
    }

    @Override
    public List<AccountType> getAllAccountTypes() {
        return account_typeRepository.findAll();
    }

    private void validateDto(AccountTypeDto account_typeDto) {
        var violations = validator.validate(account_typeDto);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Invalid DTO " + account_typeDto);
        }
    }
}
