package com.egorkhaziev.ylab.core.controllers;

import com.egorkhaziev.ylab.core.model.JSONTemplate;
import com.egorkhaziev.ylab.core.services.SaveViewInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class SaveController {

    private final SaveViewInterface saveGameView;

    @PostMapping("/uploadJSON")
    public void uploadJSON(@RequestBody JSONTemplate game) {
        saveGameView.playSaveGame(game.getGamePlay());
    }

}
