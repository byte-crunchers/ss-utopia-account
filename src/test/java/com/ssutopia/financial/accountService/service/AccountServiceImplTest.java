package com.ssutopia.financial.accountService.service;

import com.ssutopia.financial.accountService.dto.AccountTypesDto;
import com.ssutopia.financial.accountService.entity.*;
import com.ssutopia.financial.accountService.exception.DuplicateAccountNameException;
import com.ssutopia.financial.accountService.repository.AccountTypeRepository;
import com.ssutopia.financial.accountService.repository.AccountsRepository;
import com.ssutopia.financial.accountService.repository.CardsRepository;
import com.ssutopia.financial.accountService.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AccountServiceImplTest {
    private static AccountTypes accountType1,  accountType2,  accountType3;
    private static CreditAccount creditAccount1,creditAccount2,creditAccount3;
    private static DebitAccount debitAccount1,debitAccount2,debitAccount3;
    private static AccountTypesDto accountTypesDto;
    private static UserAccount userAccount1,userAccount2,userAccount3;

    private final AccountTypeRepository repository = Mockito.mock(AccountTypeRepository.class);
    private final AccountTypeService service = new AccountTypeServiceImpl(repository);

    private final AccountsRepository accountsRepository = Mockito.mock(AccountsRepository.class);
    private final AccountService accountService = new AccountServiceImpl(accountsRepository);

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final UserService userService = new UserServiceImpl(userRepository);


    private final CardsRepository cardsRepository = Mockito.mock(CardsRepository.class);
    private final CardService cardService = new CardServiceImpl(userRepository,cardsRepository,accountsRepository,repository) ;



    @BeforeAll
    static void beforeAll(){

        userAccount1 = UserAccount.builder()
                .id(1L)
                .build();

        userAccount2 = UserAccount.builder()
                .id(2L)
                .build();

        userAccount3 = UserAccount.builder()
                .id(3L)
                .build();

     debitAccount1 = DebitAccount.builder()
     .card_num(399923393855048L)
     .balance(3000f)
     .build();




        debitAccount2 = DebitAccount.builder()
                .card_num(499923393855048L)
                .balance(3000f)
                .build();

        debitAccount3 = DebitAccount.builder()
                .card_num(599923393855048L)
                .balance(3000f)
                .build();


        creditAccount1 = CreditAccount.builder()
             .card_num(799923393855048L)
             .balance(9000f)
             .build();

        creditAccount2 = CreditAccount.builder()
                .card_num(899923393855048L)
                .balance(9000f)
                .build();

        creditAccount3 = CreditAccount.builder()
                .card_num(999923393855048L)
                .balance(9000f)
                .build();


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
        Mockito.reset(userRepository);
        Mockito.reset(accountsRepository);
        Mockito.reset(cardsRepository);
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
    void test_getAllCreditCard_ReturnsAllCreditCardWithExpectedValuesOnSuccess(){
        when(cardsRepository.findAllCreditCard()).thenReturn(List.of(creditAccount1,creditAccount2,creditAccount3));
        var creditAccount = cardService.getCreditCards();
        var expectedCreditCards = List.of(creditAccount1,creditAccount2,creditAccount3);
        assertEquals(expectedCreditCards,creditAccount);
    }


    @Test
    void test_getAllUserAccount_ReturnsAllUserAccountWithExpectedValuesOnSuccess(){
        when(accountsRepository.findAllAccounts()).thenReturn(List.of(userAccount1,userAccount2,userAccount3));
        var userAccount = accountService.getAllAccounts();
        var expectedUserAccount = List.of(userAccount1,userAccount2,userAccount3);
        assertEquals(expectedUserAccount,userAccount);
    }


    @Test
    void test_getAllDebitCard_ReturnsAllDebitCardWithExpectedValuesOnSuccess(){
        when(cardsRepository.findAllDebitCard()).thenReturn(List.of(debitAccount1,debitAccount2,debitAccount3));
        var debitAccount = cardService.getDebitCards();
        var expectedDebitCards = List.of(debitAccount1,debitAccount2,debitAccount3);
        assertEquals(expectedDebitCards,debitAccount);
    }


    @Test
    void test_getAllAccountTypes_ReturnsAllAccountTypes() {
        when(repository.findAll()).thenReturn(List.of( accountType1, accountType2, accountType3));

        var accountTypes = service.getAllAccountTypes();
        var expectedAccountTypes = List.of( accountType1, accountType2, accountType3);

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
