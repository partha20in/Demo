package com.wallet.cloud.sample.demo.controller;

import java.math.BigDecimal;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.cloud.sample.demo.model.Account;
import com.wallet.cloud.sample.demo.service.AccountServiceImpl;

@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {

	@Autowired
	private AccountServiceImpl accser;
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Page<Account> getAccounts(@RequestParam(value = "number") int number, @RequestParam(value = "size") int size,
			@RequestParam(value = "sort") String sort) {

		logger.debug("In getAccount list API");

		return accser.getAllAccounts(number, size, sort);

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/credit/{name}/{amount}/{transactionId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
	public Response<Account> addCreditsAccounts(@PathVariable(value = "name") String name,
			@PathVariable(value = "amount") BigDecimal amount,
			@PathVariable(value = "transactionId") Long transactionId) {
		logger.debug("In addCreditAccounts API");
		Account acc = accser.creditAccount(name, amount, transactionId);
		if (acc != null) {
			return Response.ok();
		}
		return Response.exception();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/debit/{name}/{amount}/{transactionId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON)
	public Response<Account> debitAccounts(@PathVariable(value = "name") String name,
			@PathVariable(value = "amount") BigDecimal amount,
			@PathVariable(value = "transactionId") Long transactionId) {
		logger.debug("In debitAccounts API");
		Account acc = accser.debitAccount(name, amount, transactionId);
		if (acc != null) {
			return Response.ok();
		}
		return Response.exception();
	}

}
