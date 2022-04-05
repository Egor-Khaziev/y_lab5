package com.egorkhaziev.ylab.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Component
@AllArgsConstructor
public class GamePlay {

    private Integer id;

    private List<Player> player;

    private Game game;

    private GameResult gameResult;

    public GamePlay() {
        this.game = new Game();
        this.player = new ArrayList<>();
        this.gameResult = new GameResult();
        this.game.setGameResult(gameResult);
        log.debug("gamePlay +");
    }


}
