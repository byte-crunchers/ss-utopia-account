package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.dto.AccountTypeDto;
import com.ssutopia.financial.accountService.entity.AccountType;
import com.ssutopia.financial.accountService.exception.DuplicateAccountNameException;
import com.ssutopia.financial.accountService.repository.AccountTypeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AccountTypeServiceImplTest {
    private static AccountType account1,  account2,  account3;
    private static AccountTypeDto accountTypeDto;
    private final AccountTypeRepository repository = Mockito.mock(AccountTypeRepository.class);
    private final AccountTypeService service = new AccountTypeServiceImpl(repository);

    @BeforeAll
    static void beforeAll(){
        account1 = AccountType.builder()
                .id(1L)
                .accountName("test1")
                .build();

        account2 = AccountType.builder()
                .id(2L)
                .accountName("test2")
                .build();


        account3 = AccountType.builder()
                .id(3L)
                .accountName("test3")
                .build();

    }
    @BeforeEach
    void beforeEach() {
        Mockito.reset(repository);
    }

    @Test
    void test_createNewAccountTypes_ReturnsAccountTypesWithExpectedValuesOnSuccess() {
        when(repository.save(any(AccountType.class))).thenReturn(account1);
        var result = service.createNewAccount_type(accountTypeDto.builder()
                .accountName(account1.getAccountName())
                .build())
                ;
        assertEquals(account1, result);
    }

    @Test
    void test_getAllAccountTypes_ReturnsAllAccountTypes() {
        when(repository.findAll()).thenReturn(List.of(account1,account2,account3));

        var accountTypes = service.getAllAccountTypes();
        var expectedAccountTypes = List.of(account1,account2,account3);

        assertEquals(expectedAccountTypes, accountTypes);
    }

    @Test
    void test_createNewAccountType_ThrowsDuplicateLoanTypeNameExceptionOnDuplicateAccountTypeNameRecord() {
        when(repository.findByAccountName(account1.getAccountName())).thenReturn(Optional.of(account1));

//        repository.save(loan2);
        assertThrows(DuplicateAccountNameException.class,
                () -> service.createNewAccount_type(accountTypeDto.builder()
                        .accountName(account1.getAccountName())
                        .build()));
    }
}
