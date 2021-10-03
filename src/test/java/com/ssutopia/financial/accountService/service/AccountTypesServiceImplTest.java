package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.dto.AccountTypesDto;
import com.ssutopia.financial.accountService.entity.AccountTypes;
import com.ssutopia.financial.accountService.repository.AccountTypeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AccountTypesServiceImplTest {
    private static AccountTypes account1,  account2,  account3;
    private static AccountTypesDto accountTypesDto;
    private final AccountTypeRepository repository = Mockito.mock(AccountTypeRepository.class);
    private final AccountTypeService service = new AccountTypeServiceImpl(repository);

    @BeforeAll
    static void beforeAll(){
        account1 = AccountTypes.builder()
                .id("Basic Credit")
                .build();

        account2 = AccountTypes.builder()
                .id("Platinum Credit")
                .build();


        account3 = AccountTypes.builder()
                .id("Plus Credit")
                .build();

    }
    @BeforeEach
    void beforeEach() {
        Mockito.reset(repository);
    }

    @Test
    void test_createNewAccountTypes_ReturnsAccountTypesWithExpectedValuesOnSuccess() {
        when(repository.save(any(AccountTypes.class))).thenReturn(account1);
        var result = service.createNewAccount_type(accountTypesDto.builder()
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

//    @Test
//    void test_createNewAccountType_ThrowsDuplicateLoanTypeNameExceptionOnDuplicateAccountTypeNameRecord() {
//        when(repository.findByAccountName(account1.getAccountName())).thenReturn(Optional.of(account1));
//
////        repository.save(loan2);
//        assertThrows(DuplicateAccountNameException.class,
//                () -> service.createNewAccount_type(accountTypeDto.builder()
//                        .accountName(account1.getAccountName())
//                        .build()));
//    }
}
