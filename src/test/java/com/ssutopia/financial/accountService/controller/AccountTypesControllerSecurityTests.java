package com.ssutopia.financial.accountService.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssutopia.financial.accountService.dto.AccountTypesDto;
import com.ssutopia.financial.accountService.entity.AccountTypes;
import com.ssutopia.financial.accountService.entity.Users;
import com.ssutopia.financial.accountService.repository.AccountTypeRepository;
import com.ssutopia.financial.accountService.repository.UserRepository;
import com.ssutopia.financial.accountService.security.JwtAuthorizationFilter;
import com.ssutopia.financial.accountService.security.JwtProperties;
import com.ssutopia.financial.accountService.service.AccountTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

@SpringBootTest
public class AccountTypesControllerSecurityTests {
    final Date expiresAt = Date.from(LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC));
    @Autowired
    WebApplicationContext wac;

    @MockBean
    JwtProperties jwtProperties;

    @MockBean
    AccountTypeService accountTypeService;

    @MockBean
    AccountTypeRepository accountTypeRepository;

    @MockBean
    AccountTypesDto accountTypesDto;

    @MockBean
    UserRepository userRepository;
    @MockBean
    JwtAuthorizationFilter jwtAuthorizationFilter;

    MockMvc mvc;


    AccountTypes mockAccountTypes = AccountTypes.builder()
            .id("Basic Credit")


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


    @BeforeEach
    void beforeEach() {
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();

        Mockito.reset(accountTypeService);
        Mockito.reset(accountTypeRepository);

        when(accountTypeRepository.save(any(AccountTypes.class))).thenReturn(mockAccountTypes);
        when(userRepository.findByUsername(any())).thenReturn(mockAdminUsers);
        when(accountTypeService.createNewAccount_type(any())).thenReturn(mockAccountTypes);

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
