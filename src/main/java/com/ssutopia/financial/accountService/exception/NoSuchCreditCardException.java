package com.ssutopia.financial.accountService.exception;

import java.util.NoSuchElementException;

public class NoSuchCreditCardException  extends NoSuchElementException {
    private final Long id;

    public NoSuchCreditCardException(Long id) {
        super("No Credit card record for Credit card number =" + id);
        this.id = id;
    }
}
