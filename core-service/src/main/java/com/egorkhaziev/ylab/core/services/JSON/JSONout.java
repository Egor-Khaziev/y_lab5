package com.egorkhaziev.ylab.core.services.JSON;


import com.egorkhaziev.ylab.core.model.GamePlay;
import com.egorkhaziev.ylab.core.model.JSONTemplate;
import com.egorkhaziev.ylab.core.services.GamePlayService;
import com.egorkhaziev.ylab.core.services.WriteSaveGame;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JSONout implements WriteSaveGame {



    @Override
    public void writeSaveGameFile(GamePlay gamePlay, Integer gameNumber) {

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        try {
            jsonStr = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(new JSONTemplate(gamePlay));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        try(FileWriter file = new FileWriter("game-"+gameNumber +".json")) {
            file.write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
