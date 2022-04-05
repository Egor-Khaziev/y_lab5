package com.egorkhaziev.ylab.core.repositories;

import com.egorkhaziev.ylab.core.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
}
