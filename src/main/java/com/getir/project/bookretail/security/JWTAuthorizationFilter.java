package com.getir.project.bookretail.security;

import com.getir.project.bookretail.util.ServiceUtils;
import com.getir.project.bookretail.util.constant.SecurityConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(SecurityConstant.HEADER_STRING);
        if (header == null || !header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        log.info("Getting token...");

        String token = request.getHeader(SecurityConstant.HEADER_STRING);

        if (token != null) {
            log.info("Token is gathered");

            Claims claims = Jwts.parser()
                    .setSigningKey(ServiceUtils.stringTransformForEncryptionOfSecretKey(SecurityConstant.SECRET_KEY, JWTAuthenticationFilter.randomNumber))
                    .parseClaimsJws(token.replace(SecurityConstant.TOKEN_PREFIX, ""))
                    .getBody();

            log.info("Got payload: " + claims.toString());

            Collection<? extends GrantedAuthority> authorities
                    = Arrays.stream(claims.get("role").toString().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            log.info("Extracted authorities: " + authorities.toString());

            User principal = new User(claims.getSubject(), "", authorities);

            return new UsernamePasswordAuthenticationToken(principal, null, authorities);
        }
        return null;
    }
}