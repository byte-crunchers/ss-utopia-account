package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.dto.AccountTypesDto;
import com.ssutopia.financial.accountService.entity.AccountTypes;
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

public class AccountServiceImplTest {
    private static AccountTypes accountType1,  accountType2,  accountType3;
    private static AccountTypesDto accountTypesDto;
    private final AccountTypeRepository repository = Mockito.mock(AccountTypeRepository.class);
    private final AccountTypeService service = new AccountTypeServiceImpl(repository);

    @BeforeAll
    static void beforeAll(){
//        account1 = AccountTypes.builder()
//                .id("Basic Credit")
//                .build();
//
//        account2 = AccountTypes.builder()
//                .id("Platinum Credit")
//                .build();
//
//
//        account3 = AccountTypes.builder()
//                .id("Plus Credit")
//                .build();
        
        accountType1 = AccountTypes.builder()
                .id("Utopia Debit5")
                .build();

        accountType2 = AccountTypes.builder()
                .id("Utopia Debit3")
                .build();


        accountType3 = AccountTypes.builder()
                .id("Utopia Debit4")
                .build();

    }
    @BeforeEach
    void beforeEach() {
        Mockito.reset(repository);
    }

    @Test
    void test_createNewAccountTypes_ReturnsAccountTypesWithExpectedValuesOnSuccess() {
        when(repository.save(any(AccountTypes.class))).thenReturn(accountType1);
        var result = service.createNewAccount_type(accountTypesDto.builder()
                .build())
                ;
        assertEquals(accountType1, result);
    }

    @Test
    void test_getAllAccountTypes_ReturnsAllAccountTypes() {
        when(repository.findAll()).thenReturn(List.of( accountType1, accountType2, accountType3));

        var accountTypes = service.getAllAccountTypes();
        var expectedAccountTypes = List.of( accountType1, accountType1, accountType1);

        assertEquals(expectedAccountTypes, accountTypes);
    }

    @Test
    void test_createNewAccountType_ThrowsDuplicateLoanTypeNameExceptionOnDuplicateAccountTypeNameRecord() {
        when(repository.findById(accountType1.getId())).thenReturn(Optional.of(accountType1));
        assertThrows(DuplicateAccountNameException.class,
                () -> service.createNewAccount_type(accountTypesDto.builder()
                        .id(accountType1.getId())
                        .build()));
    }




}
