package com.egorkhaziev.ylab.core.repositories;


import com.egorkhaziev.ylab.core.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {
}
