package com.egorkhaziev.ylab.core.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Data
@Slf4j
public class GameMap {

    private char[][] gameMap;

    private int gameCountDown;


}
