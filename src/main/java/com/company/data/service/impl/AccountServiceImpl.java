package com.company.data.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.data.security.AuthoritiesConstants;
import com.company.data.security.jwt.TokenProvider;
import com.company.data.service.AccountService;
@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	@Autowired
	private TokenProvider tokenProvider;
	@Override
	public String getToken(String identity) {
		 Authentication authentication = createAuthentication(identity);
		 return tokenProvider.createToken(authentication, false);
	}
	private Authentication createAuthentication(String identity) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.USER));
        return new UsernamePasswordAuthenticationToken(identity, identity, authorities);
    }

}
