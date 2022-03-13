package com.getir.project.bookretail.security;

import com.getir.project.bookretail.entity.Customer;
import com.getir.project.bookretail.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public UserDetailsServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.info("Looking for the User: " + email + " from DB...");

        Optional<Customer> normalUser = customerRepository.findByEmail(email);

        if (normalUser.isEmpty()) {
            throw new UsernameNotFoundException("The user is not found !");
        }

        log.info("User: " + email + " has found");

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Roles.ROLE_USER.getAuthority()));

        return new org.springframework.security.core.userdetails.User(normalUser.get().getEmail(), normalUser.get().getPassword(), authorities);
    }

}