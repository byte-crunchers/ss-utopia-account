package com.ssutopia.financial.accountService.controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.ssutopia.financial.accountService.dto.CreditLimitDto;
import com.ssutopia.financial.accountService.dto.UserInfoDto;
import com.ssutopia.financial.accountService.entity.*;
import com.ssutopia.financial.accountService.service.AccountService;
import com.ssutopia.financial.accountService.service.UserService;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ssutopia.financial.accountService.dto.PaymentDto;
import com.ssutopia.financial.accountService.dto.UserInfoDto;
import com.ssutopia.financial.accountService.entity.Accounts;
import com.ssutopia.financial.accountService.entity.UserAccount;
import com.ssutopia.financial.accountService.service.AccountService;
import com.ssutopia.financial.accountService.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


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

	@PutMapping(value = "/{accountId}",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Void> suspendAccount(@RequestBody boolean status,@PathVariable("accountId") Long accountId){
    	accountService.suspendAccountById(status,accountId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{accountId}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long accountId) {
		log.info("DELETE id=" + accountId);
		accountService.deleteAccountById(accountId);
		return ResponseEntity.noContent().build();
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


    // edit account information
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> updateAccount(@RequestBody UserAccount accountInfo) {
		
		System.out.println("Update account info:");
		accountInfo.printFields();
		
		Accounts a = accountService.updateAccount(accountInfo);
		if(a != null)
			return ResponseEntity.noContent().build();  //status code 204
		else
			return ResponseEntity.unprocessableEntity().build();  //status code 422
	}

	// loan payment
	@PostMapping(path = "/loanpayment", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> makeLoanPayment(@RequestBody PaymentDto paymentForm) {

		System.out.println("Payment amount = $" + paymentForm.getAmount());

		Accounts a = accountService.payLoan(paymentForm.getOriginId(), paymentForm.getAmount());
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

	// receive card payment form, store in db, & print to console
	@PostMapping(path = "/cardpayment", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<?> makeCardPayment(@RequestBody PaymentDto paymentForm) {

		System.out.println("Received a new card payment:");

		delay();

		paymentForm.printFields();
		
		CardPayment a = accountService.payCard(paymentForm.getOriginId(), paymentForm.getDestinationId(), paymentForm.getAmount());
		if(a != null)  //valid payment
		{
			System.out.println("Saved payment entity:");
			a.printFields();
			
			// set location header
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(a.getId())
					.toUri();

			// return status code 201
			return ResponseEntity.created(location).build();
		}
		else  //invalid payment
		{
			System.out.println("Payment failed: insufficient funds");
			
			// return status code 422
			return ResponseEntity.unprocessableEntity().build();
		}
		
	}

	// get 1 card payment by id
	@GetMapping(path = "/cardpayment/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Optional<CardPayment> getCardPayment(@PathVariable Long id) {
		return accountService.getCardPayment(id);
	}

	// pretend to think for a few seconds while processing the form
	private void delay() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
