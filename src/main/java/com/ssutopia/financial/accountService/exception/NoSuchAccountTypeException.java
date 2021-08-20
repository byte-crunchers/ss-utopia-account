package com.ssutopia.financial.accountService.exception;

import java.util.NoSuchElementException;

public class NoSuchAccountTypeException extends NoSuchElementException {

    private final Long AccountTypeId;

    public NoSuchAccountTypeException(Long AccountTypeId) {
        super("No AccountType record for id=" + AccountTypeId);
        this.AccountTypeId = AccountTypeId;
    }
    public Long getAccountTypeId() {
        return AccountTypeId;
    }
}
