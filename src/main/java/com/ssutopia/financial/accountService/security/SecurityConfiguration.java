package com.ssutopia.financial.accountService.security;

import com.ssutopia.financial.accountService.controller.EndpointConstants;
import com.ssutopia.financial.accountService.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserPrincipalDetailsService userPrincipalDetailsService;
    private UserRepository userRepository;

    public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService, UserRepository userRepository) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.userRepository = userRepository;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.cors().and().csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.userRepository))
                .authorizeRequests()
                //for Admin role to create card types
                .antMatchers(HttpMethod.POST, EndpointConstants.API_V_0_1_ACCOUNTTYPES).hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, EndpointConstants.API_V_0_1_ACCOUNTS).hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, EndpointConstants.API_V_0_1_CARDS+"/credit").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, EndpointConstants.API_V_0_1_CARDS+"/debit").hasRole("ADMIN")
                //for Admin and user role to view card types
                .antMatchers(HttpMethod.GET,EndpointConstants.API_V_0_1_ACCOUNTTYPES).hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated();  }
    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
