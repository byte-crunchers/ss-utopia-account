package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.dto.AccountTypesDto;
import com.ssutopia.financial.accountService.entity.AccountTypes;
import com.ssutopia.financial.accountService.exception.DuplicateAccountNameException;
import com.ssutopia.financial.accountService.exception.NoSuchAccountTypeException;
import com.ssutopia.financial.accountService.repository.AccountTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
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
    public AccountTypes createNewAccount_type(AccountTypesDto account_typeDto) {
        validateDto(account_typeDto);

        account_typeRepository.findById(account_typeDto.getId()).
                ifPresent(account_type -> {
                    throw new DuplicateAccountNameException(account_typeDto.getId());
                });
        var account_type = AccountTypes.builder()
              .id(account_typeDto.getId())
              .annual_fee(account_typeDto.getAnnual_fee())
                .cashBack(account_typeDto.getCashBack())
                .late_fee(account_typeDto.getLate_fee())
                .savings_interest(account_typeDto.getSavings_interest())
                .foodie_pts(account_typeDto.getFoodie_pts())
                .build();
        account_type = account_typeRepository.save(account_type);
        return account_type;
    }

    @Override
    public AccountTypes getAccountTypeById(String id) {
        return account_typeRepository.findById(id)
                .orElseThrow(()->new NoSuchAccountTypeException(id));
    }

    @Override
    public List<AccountTypes> getAllAccountTypes() {
        return account_typeRepository.findAll();
    }

    private void validateDto(AccountTypesDto account_typeDto) {
        var violations = validator.validate(account_typeDto);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Invalid DTO " + account_typeDto);
        }
    }
}
