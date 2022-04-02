package com.egorkhaziev.ylab.core.services;

import com.egorkhaziev.ylab.core.logic.GameMap;
import com.egorkhaziev.ylab.core.utils.Dot;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@Data
public class MapJobService implements MapJobInterface {

    private GameMap map;
    private final Dot dot;

    @PostConstruct
    private void init() {
        map = new GameMap();
    }

    public void initMap() {
        map = new GameMap();
        char[][] newMap = new char[3][3];
        int gameCountDown = 0;

        for (char[] line : newMap) {
            for (int i = 0; i < line.length; i++) {
                line[i] = getEMPTY_DOT();
                gameCountDown++;
            }
        }

        map.setGameCountDown(gameCountDown);
        map.setGameMap(newMap);
    }

    public void paintMap() {
        for (int i = 0; i < map.getGameMap().length; i++) {
            System.out.println();
            System.out.print("|");
            for (int j = 0; j < map.getGameMap()[i].length; j++) {
                System.out.print(map.getGameMap()[i][j] + "|");
            }
        }
        System.out.println();
        log.debug("paint map +");
    }

    public char[][] getMap() {
        return map.getGameMap();
    }

    public char getX_DOT() {
        return dot.getX_DOT();
    }

    public char getEMPTY_DOT() {
        return dot.getEMPTY_DOT();
    }

    public char getO_DOT() {
        return dot.getO_DOT();
    }

    public void mapCountDownMinus() {
        map.setGameCountDown(map.getGameCountDown() - 1);
    }

    public int getMapCountDown() {
        return map.getGameCountDown();
    }
}
