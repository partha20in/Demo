package com.wallet.cloud.sample.demo.controller;

import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wallet.cloud.sample.demo.model.Player;
import com.wallet.cloud.sample.demo.service.PlayerServiceImpl;
//import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/api/v1/players")
public class PlayerController {

	private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

	@Autowired
	PlayerServiceImpl ps;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Page<Player> getPlayers(@RequestParam(value = "number") int number, @RequestParam(value = "size") int size,
			@RequestParam(value = "sort") String sort) {
		logger.debug("In getPlayersList API");
		return ps.getAllPlayerDetails(number, size, sort);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Response<Player> insertPlayers(@RequestBody Player pl) {
		logger.debug("In insertPlayers API");
		Player pla = ps.insertNewPlayerDetails(pl);
		if (pla != null) {
			return Response.ok();
		} else {
			return Response.exception();
		}
	}

}
