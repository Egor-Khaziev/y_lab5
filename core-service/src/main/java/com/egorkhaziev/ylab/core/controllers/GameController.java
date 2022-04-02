package com.egorkhaziev.ylab.core.controllers;

import com.egorkhaziev.ylab.core.api.XORequest;
import com.egorkhaziev.ylab.core.api.XOResponse;
import com.egorkhaziev.ylab.core.services.GamePlayRestResponseInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GameController {

    private final GamePlayRestResponseInterface gamePlayService;

    @GetMapping("/new")
    public XOResponse startNewGame(@RequestParam String player1, @RequestParam String player2) {
        log.warn("new game");
        return gamePlayService.responseNewGame(player1, player2);
    }

    @PostMapping("/step")
    public XOResponse step(@RequestBody XORequest request) {

        log.warn("step");
        return gamePlayService.responseStep(request);
    }

}
