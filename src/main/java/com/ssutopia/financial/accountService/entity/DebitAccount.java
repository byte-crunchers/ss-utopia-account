package com.ssutopia.financial.accountService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebitAccount {

    private Long card_num;

    private String first_name;

    private String last_name;

    private Float balance;

    private Integer pin;

    private LocalDate exp_date;

    private String fullName;

    private Float saving_interest;

    private Float annual_fee;


    public DebitAccount(Long card_num, String first_name, String last_name, Float balance, Integer pin, 
    		LocalDate exp_date, Float saving_interest, Float annual_fee) {

        this.card_num = card_num;
        this.first_name = first_name;
        this.last_name = last_name;
        this.balance = balance;
        this.fullName = first_name+ " "+last_name;
        if(pin == null)
        	this.pin = 0;
        else
        	this.pin = pin;
        this.exp_date = exp_date;
        this.saving_interest = saving_interest;
        this.annual_fee = annual_fee;

    }

    public float getSaving_interest() {
        return saving_interest;
    }

    public void setSaving_interest(Float saving_interest) {
        this.saving_interest = saving_interest;
    }

    public float getAnnual_fee() {
        return annual_fee;
    }

    public void setAnnual_fee(Float annual_fee) {
        this.annual_fee = annual_fee;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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


    public Long getCard_num() {
        return card_num;
    }

    public void setCard_num(Long card_num) {
        this.card_num = card_num;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }


    public LocalDate getExp_date() {
        return exp_date;
    }

    public void setExp_date(LocalDate exp_date) {
        this.exp_date = exp_date;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}
