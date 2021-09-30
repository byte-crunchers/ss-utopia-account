package com.ssutopia.financial.accountService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAccount {
    private Long id;

    private String first_name;

    private String last_name;

    private Long Account_type;

    private float balance;

    private float debt_interest;

    private float saving_interest;

    private float annual_fee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public long getAccount_type() {
        return Account_type;
    }

    public void setAccount_type(long account_type) {
        Account_type = account_type;
    }

    public void setAccount_type(Long account_type) {
        Account_type = account_type;
    }

    public float getSaving_interest() {
        return saving_interest;
    }

    public void setSaving_interest(float saving_interest) {
        this.saving_interest = saving_interest;
    }

    public float getAnnual_fee() {
        return annual_fee;
    }

    public void setAnnual_fee(float annual_fee) {
        this.annual_fee = annual_fee;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getDebt_interest() {
        return debt_interest;
    }

    public void setDebt_interest(float debt_interest) {
        this.debt_interest = debt_interest;
    }
}
