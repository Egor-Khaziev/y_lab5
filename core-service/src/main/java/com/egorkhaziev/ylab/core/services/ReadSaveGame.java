package com.egorkhaziev.ylab.core.services;

import com.egorkhaziev.ylab.core.model.GamePlay;

public interface ReadSaveGame {
    GamePlay readFile(String fileName)throws Exception;
}
