package com.egorkhaziev.ylab.core.repositories;

import com.egorkhaziev.ylab.core.model.GamePlay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePlayRepository extends JpaRepository<GamePlay, Integer> {
}
