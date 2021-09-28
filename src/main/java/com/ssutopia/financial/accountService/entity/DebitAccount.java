package com.ssutopia.financial.accountService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebitAccount {
    private Long id;

    private String card_num;

    private int pin;

    private int cvc1;

    private int cvc2;

    private Date exp_date;

    private float balance;

    private String first_name;

    private String last_name;

    public Long getId() {
        return id;
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
}
