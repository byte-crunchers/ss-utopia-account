package com.ssutopia.financial.accountService.entity;

import java.lang.reflect.Field;

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

    private String fullName;

    private String Account_type;

    private float balance;

    private float debt_interest;

    private float saving_interest;

    private float annual_fee;
    
    private Float payment_due;
    
    private Integer credit_limit;
    
    private Long card_num;
    
    private Boolean confirmed, approved, active;

    public UserAccount(Long id, String first_name, String last_name, String account_type, float balance, float debt_interest, float saving_interest, float annual_fee, 
    		Float payment_due, Integer credit_limit, Long card_num, Boolean confirmed, Boolean approved, Boolean active) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.fullName = first_name+ " "+last_name;
        this.Account_type = account_type;
        this.balance = balance;
        this.debt_interest = debt_interest;
        this.saving_interest = saving_interest;
        this.annual_fee = annual_fee;
        this.payment_due = payment_due;
        this.credit_limit = credit_limit;
        this.card_num = card_num;
        this.confirmed = confirmed;
        this.approved = approved;
        this.active = active;
    }

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAccount_type() {
        return Account_type;
    }


    public void setAccount_type(String account_type) {
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

	public Integer getCredit_limit() {
		return credit_limit;
	}

	public void setCredit_limit(Integer credit_limit) {
		this.credit_limit = credit_limit;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Float getPayment_due() {
		return payment_due;
	}

	public void setPayment_due(Float payment_due) {
		this.payment_due = payment_due;
	}
	

	// print all variables to console
	public void printFields() {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		result.append("{" + newLine);

		// determine fields declared in this class only (no fields of superclass)
		Field[] fields = this.getClass().getDeclaredFields();

		// print field names paired with their values
		for (Field field : fields) {
			try {
				result.append("    " + field.getName() + " = " + field.get(this) + newLine);
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}
		}
		result.append("}");

		System.out.println(result.toString());
	}

	public Long getCard_num() {
		return card_num;
	}

	public void setCard_num(Long card_num) {
		this.card_num = card_num;
	}

}
