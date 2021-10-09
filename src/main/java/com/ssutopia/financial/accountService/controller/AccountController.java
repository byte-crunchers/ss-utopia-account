package com.ssutopia.financial.accountService.controller;

import com.ssutopia.financial.accountService.entity.LoanPayment;
import com.ssutopia.financial.accountService.dto.UserInfoDto;
import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.entity.UserAccount;
import com.ssutopia.financial.accountService.service.AccountService;
import com.ssutopia.financial.accountService.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointConstants.API_V_0_1_ACCOUNTS)
public class AccountController {


    private final AccountService accountService;
    private final UserService userService;
    
    //endpoints for get all accounts

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<UserAccount>> getAllAccountTypes() {
        List<UserAccount> accounts = accountService.getAllAccounts();
        if (accounts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(accounts);
    }

    //get user info DTO for autofilling forms
    @GetMapping(value = "/userinfo/{name}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<UserInfoDto>> getUserInfo(@PathVariable String name) {
        List<UserInfoDto> userInfo = userService.getUserInfo(name);
        if (userInfo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userInfo);
    }


	// update account balance
	@PostMapping(path = "/payment", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> updateBalance(@RequestBody LoanPayment payment) {

		System.out.println("Loan payment = $" + payment.getAmount());

		Accounts a = accountService.updateBalance(payment.getAccount(), payment.getAmount());
		if(a != null)  //valid payment
		{
			System.out.println("Checking account balance = $" + a.getBalance());
			
			// return status code 200
			return ResponseEntity.ok().build();
		}
		else  //invalid payment
		{
			System.out.println("Payment failed: insufficient funds");
			
			// return status code 422
			return ResponseEntity.unprocessableEntity().build();
		}
		
	}
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
    
}
