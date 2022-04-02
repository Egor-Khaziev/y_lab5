package com.egorkhaziev.ylab.core.services;

import com.egorkhaziev.ylab.core.api.XORequest;
import com.egorkhaziev.ylab.core.api.XOResponse;

public interface GamePlayRestResponseInterface {

    XOResponse responseNewGame(String player1, String player2);
    XOResponse responseStep(XORequest request);
}
