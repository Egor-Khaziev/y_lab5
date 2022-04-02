package com.egorkhaziev.ylab.core.logic;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Data
@Slf4j
public class GameMap {

    private char[][] gameMap;

    private int gameCountDown;

    private final char EMPTY_DOT = '*';
    private final char X_DOT = 'X';
    private final char O_DOT = 'O';
}
