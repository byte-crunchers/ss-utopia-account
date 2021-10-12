package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.dto.AccountTypesDto;
import com.ssutopia.financial.accountService.entity.AccountTypes;

import java.util.List;

public interface AccountTypeService {
    AccountTypes createNewAccount_type(AccountTypesDto account_typeDto);
    AccountTypes getAccountTypeById(String id);
    List<AccountTypes> getAllAccountTypes();

}
