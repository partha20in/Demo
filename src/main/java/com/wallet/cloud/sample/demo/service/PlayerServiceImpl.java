package com.wallet.cloud.sample.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.wallet.cloud.sample.demo.exception.InvalidParametersException;
import com.wallet.cloud.sample.demo.exception.PlayerAlreadyExistsException;
import com.wallet.cloud.sample.demo.model.Account;
import com.wallet.cloud.sample.demo.model.Player;
import com.wallet.cloud.sample.demo.repository.AccountRepository;
import com.wallet.cloud.sample.demo.repository.PlayerRepository;
import com.wallet.cloud.sample.demo.util.PageUtil;



@Service("PlayerService")
public class PlayerServiceImpl extends PageUtil implements PlayerService {

	@Autowired
	PlayerRepository playrepo;

	@Autowired
	AccountRepository accountRepo;

	private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);

	public Page<Player> getAllPlayerDetails(int number, int size, String sort) {
		logger.debug("Inside getAllPlayerDetails API");
		Pageable pageableObj = null;
		try {
			pageableObj = PageUtil.createPageRequest(number, size, sort);
			if (pageableObj == null) {
				throw new InvalidParametersException("Request parameter invalid");
			}
		} catch (InvalidParametersException e) {
			logger.error(e.getMessage());
		}
		return playrepo.findAll(pageableObj);
	}

	public Player insertNewPlayerDetails(Player pl) {
		try {
			logger.debug("Inside insertNewPlayerDetails service");
			Account a = new Account();
			a.setBalance(pl.getAccount().getBalance());
			a.setAccountNumber(pl.getAccount().getAccountNumber());

			Player p = new Player(pl.getName(), pl.getGender(), pl.getAge(), a);
			Optional<Player> player = playrepo.findByName(pl.getName());
			Optional<Account> account = accountRepo.findByAccountNumber(pl.getAccount().getAccountNumber());
			if (p != null && a != null && !(player.isPresent()) && !(account.isPresent())) {
				accountRepo.save(a);
				return playrepo.save(p);

			} else {
				throw new PlayerAlreadyExistsException("Player or account number already exist ");
			}

		} catch (PlayerAlreadyExistsException e) {

			logger.error(e.getMessage());
		}
		return null;

	}

}
