package com.ssutopia.financial.accountService.exception;

public class AlreadySuspendedException extends IllegalStateException{
    private final Long id;

    public AlreadySuspendedException(Long id) {
        super("An account with the id '" + id + "' already suspend.");
        this.id = id;
    }

    public Long getName(){
        return id;
    }

}
