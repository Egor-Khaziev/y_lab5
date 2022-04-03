package com.egorkhaziev.ylab.core.services;

import com.egorkhaziev.ylab.core.model.GamePlay;

public interface WriteSaveGame {

    void writeSaveGameFile(GamePlay gamePlay, Integer gameNumber);

}
