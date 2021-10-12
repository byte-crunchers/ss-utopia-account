package com.ssutopia.financial.accountService.exception;

import java.util.NoSuchElementException;

public class NoSuchAccountTypeException extends NoSuchElementException {

    private final String AccountTypeId;

    public NoSuchAccountTypeException(String AccountTypeId) {
        super("No AccountType record for id=" + AccountTypeId);
        this.AccountTypeId = AccountTypeId;
    }
    public String getAccountTypeId() {
        return AccountTypeId;
    }
}
