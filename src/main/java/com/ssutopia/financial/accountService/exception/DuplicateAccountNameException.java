package com.ssutopia.financial.accountService.exception;

public class DuplicateAccountNameException extends IllegalStateException{
    private  final String name;


    public DuplicateAccountNameException(String name) {
        super("An account type with the name '" + name + "' already exists.");
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
