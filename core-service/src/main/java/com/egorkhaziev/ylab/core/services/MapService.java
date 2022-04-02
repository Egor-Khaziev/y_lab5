package com.egorkhaziev.ylab.core.services;

import com.egorkhaziev.ylab.core.logic.GameMap;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@Data
public class MapService {

    private GameMap map;

    @PostConstruct
    private void init(){
        map = new GameMap();
    }

    public void initMap() {
        map = new GameMap();
        char[][] newMap = new char[3][3];
        int gameCountDown = 0;

        for (char[] line : newMap) {
            for (int i= 0;i<line.length;i++ ) {
                line[i] = map.getEMPTY_DOT();
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

    public char[][] getMap(){
        return map.getGameMap();
    }

    public char getX_DOT(){
        return map.getX_DOT();
    }

    public char getO_DOT(){
        return map.getO_DOT();
    }

    public void gameCountDownMinus(){
            map.setGameCountDown(map.getGameCountDown()-1);
    }

    public int getGameCountDown() {
        return map.getGameCountDown();
    }
}
