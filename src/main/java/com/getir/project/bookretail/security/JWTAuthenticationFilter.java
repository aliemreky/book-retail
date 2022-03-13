package com.getir.project.bookretail.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.project.bookretail.entity.Customer;
import com.getir.project.bookretail.util.ServiceUtils;
import com.getir.project.bookretail.util.constant.SecurityConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static int randomNumber = getRandomNumber();

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            log.info("Attempting to authenticate...");

            Customer credentials = new ObjectMapper().readValue(req.getInputStream(), Customer.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(),
                            credentials.getPassword(),
                            new ArrayList<>())
            );
        } catch (Exception e) {
            log.error("Authentication has failed: ", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {

        log.info("Building JWT...");

        Map<String, Object> authorities = authorizationGetter(auth);

        String token = Jwts.builder()
                .setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
                .addClaims(authorities)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, ServiceUtils.stringTransformForEncryptionOfSecretKey(SecurityConstant.SECRET_KEY, randomNumber))
                .compact();
        res.addHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOKEN_PREFIX + token);

        log.info("JWT is: " + token);

        log.info("Authentication is successful");
    }

    private Map<String, Object> authorizationGetter(Authentication auth) {
        String role = ((org.springframework.security.core.userdetails.User) auth.getPrincipal())
                .getAuthorities()
                .contains(new SimpleGrantedAuthority(Roles.ROLE_USER.getAuthority())) ? Roles.ROLE_USER.getAuthority() : Roles.ROLE_ADMIN.getAuthority();

        Map<String, Object> authorities = new HashMap<>();
        authorities.put("role", role);

        log.info("Authorities are: " + authorities);

        return authorities;
    }

    private static int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(5) + 1;
    }

}