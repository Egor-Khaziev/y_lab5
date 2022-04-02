package com.egorkhaziev.ylab.core.services;

import com.egorkhaziev.ylab.core.logic.model.Player;

public interface GamePlayLogicInterface {

    void startNewGame(String player1Name, String player2Name);
    char[][] step (int x, int y, Player player);

}
