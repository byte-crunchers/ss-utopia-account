package com.ssutopia.financial.accountService.security;

import com.ssutopia.financial.accountService.entity.Users;
import com.ssutopia.financial.accountService.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users users = this.userRepository.findByUsername(s);
        UserPrincipal userPrincipal = new UserPrincipal(users);

        return userPrincipal;
    }

}
