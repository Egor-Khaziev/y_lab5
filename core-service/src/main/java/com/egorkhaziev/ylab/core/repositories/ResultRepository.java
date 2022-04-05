package com.egorkhaziev.ylab.core.repositories;

import com.egorkhaziev.ylab.core.model.GamePlay;
import com.egorkhaziev.ylab.core.model.GameResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<GameResult, Integer> {

}
