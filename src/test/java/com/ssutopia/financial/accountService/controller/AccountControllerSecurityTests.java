package com.ssutopia.financial.accountService.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssutopia.financial.accountService.dto.AccountTypesDto;
import com.ssutopia.financial.accountService.entity.*;
import com.ssutopia.financial.accountService.repository.AccountTypeRepository;
import com.ssutopia.financial.accountService.repository.AccountsRepository;
import com.ssutopia.financial.accountService.repository.CardsRepository;
import com.ssutopia.financial.accountService.repository.UserRepository;
import com.ssutopia.financial.accountService.security.JwtAuthorizationFilter;
import com.ssutopia.financial.accountService.security.JwtProperties;
import com.ssutopia.financial.accountService.service.AccountService;
import com.ssutopia.financial.accountService.service.AccountTypeService;
import com.ssutopia.financial.accountService.service.CardService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
public class AccountControllerSecurityTests {
    final Date expiresAt = Date.from(LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC));
    @Autowired
    WebApplicationContext wac;

    @MockBean
    JwtProperties jwtProperties;

    @MockBean
    AccountTypeService accountTypeService;


    @MockBean
    CardsRepository cardsRepository;

    @MockBean
    CardService cardService;

    @MockBean
    CardController cardController;

    @MockBean
    AccountTypeRepository accountTypeRepository;

    @MockBean
    AccountsRepository accountRepository;

    @MockBean
    AccountService accountService;

    @MockBean
    AccountTypesDto accountTypesDto;

    @MockBean
    UserRepository userRepository;
    @MockBean
    JwtAuthorizationFilter jwtAuthorizationFilter;

    MockMvc mvc;


    UserAccount mockUserAccount1 = UserAccount.builder()
            .id(1L)
            .Account_type("debit")
            .last_name("Amy")
            .first_name("po")
            .balance(4000f)
            .saving_interest(0.04f)
            .debt_interest(0.05f)
            .build();

    UserAccount mockUserAccount2 = UserAccount.builder()
            .id(2L)
            .Account_type("debit")
            .last_name("Amy")
            .first_name("po")
            .balance(4000f)
            .saving_interest(0.04f)
            .debt_interest(0.05f)
            .build();

    UserAccount mockUserAccount3 = UserAccount.builder()
            .id(3L)
            .Account_type("debit")
            .last_name("Amy")
            .first_name("po")
            .balance(4000f)
            .saving_interest(0.04f)
            .debt_interest(0.05f)
            .build();


    DebitAccount mockDebitAccount1 = DebitAccount.builder()
            .card_num(9999237631274614L)
            .balance(9000f)
            .first_name("jim")
            .last_name("il")
            .build();

    DebitAccount mockDebitAccount2 = DebitAccount.builder()
            .card_num(9999237631274614L)
            .balance(9000f)
            .first_name("jim")
            .last_name("il")
            .build();

    DebitAccount mockDebitAccount3 = DebitAccount.builder()
            .card_num(9999237631274614L)
            .balance(9000f)
            .first_name("jim")
            .last_name("il")
            .build();




    CreditAccount mockCreditAccount1 = CreditAccount.builder()
            .card_num(9999232631274614L)
            .cvc1(234)
            .cvc2(456)
            .balance(9000f)
            .first_name("jim")
            .last_name("il")
            .limit(10000)
            .build();

    CreditAccount mockCreditAccount2 = CreditAccount.builder()
            .card_num(9992232631274614L)
            .cvc1(234)
            .cvc2(456)
            .balance(9000f)
            .first_name("jim")
            .last_name("il")
            .limit(10000)
            .build();

    CreditAccount mockCreditAccount3 = CreditAccount.builder()
            .card_num(9991232631274614L)
            .cvc1(234)
            .cvc2(456)
            .balance(9000f)
            .first_name("jim")
            .last_name("il")
            .limit(10000)
            .build();


    AccountTypes mockAccountTypes = AccountTypes.builder()
            .id("Basic Credit")
            .foodie_pts(0.0f)
            .savings_interest(0.00f)
            .cashBack(0.01f)
            .annual_fee(0)
            .late_fee(29.00f)
            .build();
    Users mockAdminUsers = Users.builder()
            .username("admin")
            .password("admin123")
            .roles("ADMIN")
            .permissions("ACCESS_TEST1,ACCESS_TEST2")
            .build();

    Users mockUsers1 = Users.builder()
            .username("adan")
            .password("adan123")
            .roles("USER")
            .permissions("")
            .build();

    Cards mockCard1 = Cards.builder()
            .card_num(499923393855048L)
            .build();


    @BeforeEach
    void beforeEach() {
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();

        Mockito.reset(accountTypeService);
        Mockito.reset(accountTypeRepository);
        Mockito.reset(accountService);
        Mockito.reset(accountRepository);
        Mockito.reset(cardsRepository);
        Mockito.reset(cardService);

        when(accountTypeRepository.save(any(AccountTypes.class))).thenReturn(mockAccountTypes);
        when(userRepository.findByUsername(any())).thenReturn(mockAdminUsers);
        when(accountRepository.findAllAccounts()).thenReturn((Arrays.asList(mockUserAccount1,mockUserAccount2,mockUserAccount3)));
        when(accountService.getAllAccounts()).thenReturn((Arrays.asList(mockUserAccount1,mockUserAccount2,mockUserAccount3)));
        when(accountTypeService.createNewAccount_type(any())).thenReturn(mockAccountTypes);
        when(cardsRepository.findAllCreditCard()).thenReturn(Arrays.asList(mockCreditAccount1,mockCreditAccount2,mockCreditAccount3));
        when(cardService.getCreditCards()).thenReturn(Arrays.asList(mockCreditAccount1,mockCreditAccount2,mockCreditAccount3));
        when(cardsRepository.findAllDebitCard()).thenReturn(Arrays.asList(mockDebitAccount1,mockDebitAccount2,mockDebitAccount3));
        when(cardService.getDebitCards()).thenReturn(Arrays.asList(mockDebitAccount1,mockDebitAccount2,mockDebitAccount3));
        when(cardService.viewCreditLimit(any())).thenReturn(1000);

    }
    String getJwt(MockUser mockUser) {
        var jwt = JWT.create()
                .withSubject(mockUser.username)
//                .withSubject(mockUser.password)
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC512("SomeSecretForJWTGeneration"));
        return "Bearer " + jwt;
    }


    @Test
    void test_getAllUserAccount_canOnlyBePerformByAdmin()throws Exception{
        mvc.perform(
                get(EndpointConstants.API_V_0_1_ACCOUNTS)
                .header("Authorization", getJwt(MockUser.ADMIN)))
                 .andExpect(status().isOk());

        mvc.perform(
                get(EndpointConstants.API_V_0_1_ACCOUNTS))
                .andExpect(status().isForbidden());

    }

    @Test
    void test_viewCreditLimit_canOnlyBePerformByAdmin()throws Exception{
        mvc.perform(
                get(EndpointConstants.API_V_0_1_CARDS+"/"+mockCard1.getCardNum())
                .header("Authorization", getJwt(MockUser.ADMIN)))
        .andExpect(status().isOk());

        mvc.perform(
                get(EndpointConstants.API_V_0_1_CARDS))
                .andExpect(status().isForbidden());
    }


    @Test
    void test_getAllUserAccount_CanBeForbiddenByNormalUser()throws Exception{
        when(userRepository.findByUsername(any())).thenReturn(mockUsers1);
        var unauthed = Arrays.asList(MockUser.DEFAULT,
                MockUser.DEFAULT,
                MockUser.MATCH_USER,
                MockUser.UNMATCH_USER
        );


        for (var user : unauthed) {
            var mockDtoAsJson = new ObjectMapper().writeValueAsString(mockAccountTypes);

            mvc
                    .perform(
                            get(EndpointConstants.API_V_0_1_ACCOUNTS)
                                    .header("Authorization", getJwt(user)))
                    .andExpect(status().isForbidden());
        }

    }

    @Test
    void test_getAllCreditAccount_CanBeForbiddenByNormalUser()throws Exception{
        when(userRepository.findByUsername(any())).thenReturn(mockUsers1);
        var unauthed = Arrays.asList(MockUser.DEFAULT,
                MockUser.DEFAULT,
                MockUser.MATCH_USER,
                MockUser.UNMATCH_USER
        );


        for (var user : unauthed) {
            var mockDtoAsJson = new ObjectMapper().writeValueAsString(mockAccountTypes);

            mvc
                    .perform(
                            get(EndpointConstants.API_V_0_1_CARDS+"/credit")
                                    .header("Authorization", getJwt(user)))
                    .andExpect(status().isForbidden());
        }

    }

    @Test
    void test_getAllDebitAccount_CanBeForbiddenByNormalUser()throws Exception{
        when(userRepository.findByUsername(any())).thenReturn(mockUsers1);
        var unauthed = Arrays.asList(MockUser.DEFAULT,
                MockUser.DEFAULT,
                MockUser.MATCH_USER,
                MockUser.UNMATCH_USER
        );


        for (var user : unauthed) {
            var mockDtoAsJson = new ObjectMapper().writeValueAsString(mockAccountTypes);

            mvc
                    .perform(
                            get(EndpointConstants.API_V_0_1_CARDS+"/debit")
                                    .header("Authorization", getJwt(user)))
                    .andExpect(status().isForbidden());
        }

    }

    @Test
    void  test_getAllCreditAccount_canOnlyBePerformByAdmin()throws Exception{
        mvc.perform(
                get(EndpointConstants.API_V_0_1_CARDS+"/credit")
                        .header("Authorization", getJwt(MockUser.ADMIN)))
                .andExpect(status().isOk());


        mvc.perform(
                get(EndpointConstants.API_V_0_1_CARDS+"/credit")
                       )
                .andExpect(status().isForbidden());
    }


    @Test
    void  test_getAllDebitAccount_canOnlyBePerformByAdmin()throws Exception{
        mvc.perform(
                get(EndpointConstants.API_V_0_1_CARDS+"/debit")
                        .header("Authorization", getJwt(MockUser.ADMIN)))
                .andExpect(status().isOk());


        mvc.perform(
                get(EndpointConstants.API_V_0_1_CARDS+"/debit")
        )
                .andExpect(status().isForbidden());
    }



    @Test
    void test_createNewAccountType_CanOnlyBePerformedByAdmin() throws Exception {
        var mockDtoAsJson = new ObjectMapper().writeValueAsString(mockAccountTypes);
        mvc
                .perform(
                        post(EndpointConstants.API_V_0_1_ACCOUNTTYPES)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mockDtoAsJson)

                                .header("Authorization", getJwt(MockUser.ADMIN)))
                .andExpect(status().isCreated());

        mvc
                .perform(
                        post(EndpointConstants.API_V_0_1_ACCOUNTTYPES)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mockDtoAsJson))

                .andExpect(status().isForbidden());
    }



    @Test
    void test_createNewAccountType_CanBeForbiddenByNormalUser() throws Exception{
        when(userRepository.findByUsername(any())).thenReturn(mockUsers1);
        var unauthed = Arrays.asList(MockUser.DEFAULT,
                MockUser.DEFAULT,
                MockUser.MATCH_USER,
                MockUser.UNMATCH_USER
        );

        for (var user : unauthed) {
            var mockDtoAsJson = new ObjectMapper().writeValueAsString(mockAccountTypes);

            mvc
                    .perform(
                            post(EndpointConstants.API_V_0_1_ACCOUNTTYPES)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(mockDtoAsJson)
                                    .header("Authorization", getJwt(user)))
                    .andExpect(status().isForbidden());
        }
    }





    enum MockUser {
        DEFAULT("dan", "ROLE_DEFAULT","dan1123","" ),
        MATCH_USER("dan", "ROLE_USER","dan123","" ),
        UNMATCH_USER("dan", "ROLE_USER","dan2123",""),
        MANAGER("manager", "ROLE_MANAGER","manager123","ACCESS_TEST1" ),
        ADMIN("admin", "ROLE_ADMIN", "admin123","ACCESS_TEST1,ACCESS_TEST2");


        final String username;
        final GrantedAuthority grantedAuthority;
        final String password;
        final String permissions;

        MockUser(String username, String grantedAuthority, String password,String permissions) {
            this.username =username;
            this.grantedAuthority = new SimpleGrantedAuthority(grantedAuthority);
            this.password = password;
            this.permissions = permissions;
        }

        public String getAuthority() {
            return grantedAuthority.getAuthority();
        }
    }

}
