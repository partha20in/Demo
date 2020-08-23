package com.wallet.cloud.sample.demo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.wallet.cloud.sample.demo.model.*;

@Repository
public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {

//	@Query("FROM Player WHERE name= ?1")
	Optional<Player> findByName(String name);

	@SuppressWarnings("unchecked")
	Player save(Player pl);

}
