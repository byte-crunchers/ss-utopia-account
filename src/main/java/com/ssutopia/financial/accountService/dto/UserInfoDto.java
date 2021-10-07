package com.ssutopia.financial.accountService.dto;


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
public class UserInfoDto {

	//DTO for autofilling card & loan signup forms
	
	private Long id;
	private String username, first_name, last_name, email, street_address, city, state;
	private Integer zip;
	private Long phone;
	
	
}
