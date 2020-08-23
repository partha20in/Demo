package com.wallet.cloud.sample.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wallet.cloud.sample.demo.model.*;

public interface PlayerService {
	Page<Player> getAllPlayerDetails(int number, int size, String sort);

	Player insertNewPlayerDetails(Player pl);

}
