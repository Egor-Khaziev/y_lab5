package com.egorkhaziev.ylab.core.model;

import com.egorkhaziev.ylab.core.model.GamePlay;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JSONTemplate {
    private GamePlay gamePlay;

    public JSONTemplate(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }
}
