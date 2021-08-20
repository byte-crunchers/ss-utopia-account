package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.dto.AccountTypeDto;
import com.ssutopia.financial.accountService.entity.AccountType;

import java.util.List;

public interface AccountTypeService {
    AccountType createNewAccount_type(AccountTypeDto account_typeDto);
    AccountType getAccountTypeById(Long id);
    List<AccountType> getAllAccountTypes();

}
