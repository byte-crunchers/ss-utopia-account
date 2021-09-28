package com.ssutopia.financial.accountService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditAccount {
    private Long id;

    private String card_num;

    private int pin;

    private int cvc1;

    private int cvc2;

    private Date exp_date;

    private float balance;
    private float debt_interest;
    private Date payment_due;
    private Date due_date;
    private int limit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getCvc1() {
        return cvc1;
    }

    public void setCvc1(int cvc1) {
        this.cvc1 = cvc1;
    }

    public int getCvc2() {
        return cvc2;
    }

    public void setCvc2(int cvc2) {
        this.cvc2 = cvc2;
    }

    public Date getExp_date() {
        return exp_date;
    }

    public void setExp_date(Date exp_date) {
        this.exp_date = exp_date;
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

    public Date getPayment_due() {
        return payment_due;
    }

    public void setPayment_due(Date payment_due) {
        this.payment_due = payment_due;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
