package com.wallet.cloud.sample.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wallet.cloud.sample.demo.model.Transaction;

public interface TransactionService {
	Page<Transaction> getAllTransactionsByAccountNumber(int number, int size, String sort, String accountnumber);

	Page<Transaction> getAllTransactionByPlayerName(int number, int size, String sort, String playerName);

}
