package com.ssutopia.financial.accountService.dto;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CardStatusDto {

	//DTO for viewing card status
	
	private Long accountId, cardNum;
	private String accountType;
	private Float balance, paymentDue, debtInterest;
	private Integer creditLimit;
	private Date dueDate, expDate;
	private Boolean active, approved, confirmed;
	
}
