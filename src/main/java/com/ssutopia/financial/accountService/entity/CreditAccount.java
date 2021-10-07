package com.ssutopia.financial.accountService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditAccount {

    private Long card_num;

    private String first_name;

    private String last_name;

    private String fullName;

    private float balance;

    private LocalDate exp_date;

    private int limit;

    private int cvc1;

    private int cvc2;

    private float payment_due;

    private Date due_date;



    public CreditAccount(Long card_num, String first_name,
                         String last_name,float balance, LocalDate exp_date, int limit, int cvc1, int cvc2
    ,float payment_due,Date due_date) {

        this.card_num = card_num;
        this.first_name = first_name;
        this.last_name = last_name;
        this.fullName = first_name+ " "+last_name;
        this.balance = balance;
        this.exp_date = exp_date;
        this.limit = limit;
        this.cvc1 = cvc1;
        this.cvc2 = cvc2;
        this.payment_due = payment_due;
        this.due_date = due_date;
    }

    public float getPayment_due() {
        return payment_due;
    }

    public void setPayment_due(float payment_due) {
        this.payment_due = payment_due;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }



    public Long getCard_num() {
        return card_num;
    }

    public void setCard_num(Long card_num) {
        this.card_num = card_num;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = first_name+" "+last_name;
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

    public LocalDate getExp_date() {
        return exp_date;
    }

    public void setExp_date(LocalDate exp_date) {
        this.exp_date = exp_date;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
