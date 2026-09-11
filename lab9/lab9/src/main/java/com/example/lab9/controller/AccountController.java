package com.example.lab9.controller;

import com.example.lab9.model.Account;
import com.example.lab9.service.AccountService;
import com.example.lab9.service.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private final AccountService accountService;
	private final DepositService depositService;

	public AccountController(AccountService accountService, DepositService depositService) {
		this.accountService = accountService;
		this.depositService = depositService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Account createAccount(@RequestBody Account account) {
		return accountService.create(account);
	}

	@GetMapping("/{id}")
	public Account getAccount(@PathVariable Long id) {
		return accountService.findById(id);
	}

	@PostMapping("/{id}/deposit")
	@ResponseStatus(HttpStatus.OK)
	public DepositResponse deposit(@PathVariable Long id, @RequestBody DepositRequest request) {
		depositService.deposit(id, request.amount());
		return new DepositResponse("Deposit successful");
	}

	public record DepositRequest(Double amount) {
	}

	public record DepositResponse(String message) {
	}
}
