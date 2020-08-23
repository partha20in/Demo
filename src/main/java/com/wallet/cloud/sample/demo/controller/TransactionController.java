package com.wallet.cloud.sample.demo.controller;

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
import com.wallet.cloud.sample.demo.model.*;
import com.wallet.cloud.sample.demo.service.*;

@RestController
@RequestMapping(value = "/api/v1/transactions")
public class TransactionController {

	@Autowired
	private TransactionServiceImpl tservice;

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/transactionhistorybyaccountnumber/{accountnumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Page<Transaction> getTransactionByAccountNumber(@RequestParam(value = "number") int number,
			@RequestParam(value = "size") int size, @RequestParam(value = "sort") String sort,
			@PathVariable(value = "accountnumber") String accountnumber) {
		logger.debug("In getTransactionByAccountNumber API");
		return tservice.getAllTransactionsByAccountNumber(number, size, sort, accountnumber);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	@RequestMapping(value = "/transactionhistorybyplayername/{playername}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public Page<Transaction> getTransactionByPlayerName(@RequestParam(value = "number") int number,
			@RequestParam(value = "size") int size, @RequestParam(value = "sort") String sort,
			@PathVariable(value = "playername") String playername) {
		logger.debug("In getTransactionByPlayerName API");

		return tservice.getAllTransactionByPlayerName(number, size, sort, playername);
	}

}
