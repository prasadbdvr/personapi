package com.company.data.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.data.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountResource {
	private final Logger log = LoggerFactory.getLogger(PersonResource.class);

	private static final String ENTITY_NAME = "personapiPerson";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final AccountService accountService;

	public AccountResource(AccountService accountService) {
		this.accountService = accountService;

	}

	@GetMapping("/token")
	public String getToken(@RequestParam(required = false, defaultValue = "anonymous") String identity) {
		return accountService.getToken(identity);
	}
}
