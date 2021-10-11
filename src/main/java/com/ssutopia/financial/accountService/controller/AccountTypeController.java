package com.ssutopia.financial.accountService.controller;

import com.ssutopia.financial.accountService.dto.AccountTypeDto;
import com.ssutopia.financial.accountService.entity.AccountType;
import com.ssutopia.financial.accountService.service.AccountTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(EndpointConstants.API_V_0_1_ACCOUNTTYPES)
@CrossOrigin
public class AccountTypeController {
    public static final String MAPPING = EndpointConstants.API_V_0_1_ACCOUNTTYPES;
    private final AccountTypeService accountTypeService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AccountType> createNewAccountType(@Valid @RequestBody AccountTypeDto accountTypeDto){
//        log.info("post account types");
        var accountType = accountTypeService.createNewAccount_type(accountTypeDto);
        var uri = URI.create(MAPPING+"/"+accountType.getId());
        return ResponseEntity.created(uri).body(accountType);
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<AccountType>> getAllAccountTypes() {
//        log.info("GET all");
        List<AccountType> AccountType = accountTypeService.getAllAccountTypes();
        if (AccountType.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(AccountType);
    }


//    @GetMapping(value = "/{AccountTypeId}",
//            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    public ResponseEntity<AccountType> getTicketById(@PathVariable Long AccountTypeId) {
////        log.info("GET id=" + ticketId);
//        return ResponseEntity.of(Optional.ofNullable(accountTypeService.getAccountTypeById(AccountTypeId)));
//    }

    @PostMapping(value = "/test")
    public String Test1(){
        return "Hi";
    }

    @GetMapping(value = "/test")
    public String Test(){
        return "Hi";
    }
}
