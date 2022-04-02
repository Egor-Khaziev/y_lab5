package com.egorkhaziev.ylab.core.services;

import com.egorkhaziev.ylab.core.logic.model.Player;

public interface PlayersStorageInterface {
    void loadPlayers();
    void authorization(String playerName1, String playerName2);
    void baseRefresh();
    void saveLogToFile(Player winPlayer, Player lossPlayer);
    void savePlayersToBD();
    void savePlayersToFile();
}
