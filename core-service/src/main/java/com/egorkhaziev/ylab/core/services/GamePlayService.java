package com.egorkhaziev.ylab.core.services;

import com.egorkhaziev.ylab.core.api.XORequest;
import com.egorkhaziev.ylab.core.api.XOResponse;
import com.egorkhaziev.ylab.core.converters.ConverterPlayer;
import com.egorkhaziev.ylab.core.exceptions.NoCreateGameException;
import com.egorkhaziev.ylab.core.repositories.GamePlayRepository;
import com.egorkhaziev.ylab.core.services.JSON.JSONout;
import com.egorkhaziev.ylab.core.services.XML.XMLout;
import com.egorkhaziev.ylab.core.model.GamePlay;
import com.egorkhaziev.ylab.core.model.Player;
import com.egorkhaziev.ylab.core.model.Step;
import com.egorkhaziev.ylab.core.exceptions.WrongInputException;
import com.egorkhaziev.ylab.core.utils.Dot;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
@Slf4j
@Data
//@RequiredArgsConstructor
public class GamePlayService implements GamePlayRestResponseInterface {

    private GamePlay gamePlay;
    private final MapJobInterface mapService;
    private final PlayerService playerService;
    private final ConverterPlayer converterPlayer;
    private final Dot dot;
    private final XMLout xmLout;
    private final JSONout jsoNout;
    private final GamePlayRepository gamePlayRepository;

    private int x = 0;
    private int y = 0;

    private boolean gameFinished;

    public int gameNumber;
    public int gameStep;

    @PostConstruct
    private void init(){
        gameNumber=100;

    }

    public XOResponse responseNewGame(String player1, String player2) {
        startNewGame(player1, player2);
        XOResponse xoResponse = new XOResponse();
        xoResponse.setPlayer1(converterPlayer.playerToDTO(playerService.getPlayer1()));
        xoResponse.setPlayer2(converterPlayer.playerToDTO(playerService.getPlayer2()));
        xoResponse.setGameMap(mapService.getMap());
        log.warn("собран ответ XOResponse на создание новой игры");
        return xoResponse;
    }

    public XOResponse responseStep(XORequest request) {
        XOResponse xoResponse = new XOResponse();
        xoResponse.setGameMap(step(request.getX(), request.getY(), converterPlayer.dtoToPlayer(request.getPlayerDTO())));
        xoResponse.setPlayer1(converterPlayer.playerToDTO(playerService.getPlayer1()));
        xoResponse.setPlayer2(converterPlayer.playerToDTO(playerService.getPlayer2()));
        if(gameFinished && gamePlay.getGameResult().getPlayer()!=null) {
            xoResponse.setWinner(converterPlayer.playerToDTO(gamePlay.getGameResult().getPlayer()));
        }
        return xoResponse;
    }

    private void startNewGame(String player1Name, String player2Name) {
        gameNumber++;
        log.debug("game creating start");
        gameStep = 0;
        mapService.initMap();
        gamePlay = new GamePlay();
        gameFinished = false;

        playerService.loadPlayers();
        playerService.authorization(player1Name, player2Name);
        playerService.getPlayer1().setSimbol((dot.getX_DOT()));
        playerService.getPlayer2().setSimbol((dot.getO_DOT()));
        gamePlay.getPlayer().add(playerService.getPlayer1());
        gamePlay.getPlayer().add(playerService.getPlayer2());
        mapService.paintMap();
    }

    private char[][] step (int x, int y, Player player){

        if(mapService.getMap()==null){
            throw new NoCreateGameException("Нет созданной игры.");
        }

        if(gameFinished){
            System.out.println("GAME IS OVER\nPLEASE START NEW GAME");
            return mapService.getMap();
        }

        if (mapService.getMap().length >= x
                && mapService.getMap()[0].length >= y
                && (mapService.getMap()[x - 1][y - 1] != 'X' && mapService.getMap()[x - 1][y - 1] != 'O')) {
            mapService.getMap()[x - 1][y - 1] = player.getSimbol();
            mapService.mapCountDownMinus();

            Step step = new Step();
            step.setPlayerId(player.getId());
            step.setX(x);
            step.setY(y);
            step.setNum(gameStep);

            gamePlay.getGame().getStep().add(step);
            gameStep++;

            log.debug("step="+ gameStep + " x="+x+" y="+y);

        } else {
            log.debug("wrong input :(");
            throw new WrongInputException("неверные значения ввода");
        }

        mapService.paintMap();
        if(winCheck(player)){return mapService.getMap();}
        if(friendlyWinCheck()){ gameFinished = true;}

        return mapService.getMap();
    }

    private boolean friendlyWinCheck() {
        if (mapService.getMapCountDown() == 0) {
            System.out.println("GAME IS OVER\nFRIENDLY WIN");
            log.debug("registered friendly win");
            saveProcedure();
            return true;
        }
        return false;
    }

    private boolean winCheck(Player player) {

        if (isWin(player.getSimbol())) {
            System.out.println("GAME IS OVER");

            gamePlay.getGameResult().setPlayer(player);

            gameFinished =true;
            //сохранение отчета в файл
            Player enemy = player.getId()== playerService.getPlayer1().getId()? playerService.getPlayer2(): playerService.getPlayer1();
            playerService.saveLogToFile(player, enemy);

            player.setSeriesWin(player.getSeriesWin() + 1);
            player.setWin(player.getWin() + 1);
            System.out.println("WINNER:\n" + player.toString());

            enemy.setSeriesWin(0);
            enemy.setLoss(enemy.getLoss() + 1);
            System.out.println("\nLoser:\n" + enemy.toString());
            saveProcedure();
        }
        return gameFinished;
    }

    private boolean isWin(char playerDot) {

        for (int i = 0; i < mapService.getMap().length; i++) { // горизонталь
            for (int j = 0; j < mapService.getMap()[i].length - 2; j++) {
                if (mapService.getMap()[i][j] == playerDot && mapService.getMap()[i][j + 1] == playerDot && mapService.getMap()[i][j + 2] == playerDot) {
                    return true;
                }
            }
        }
        for (int i = 0; i < mapService.getMap().length - 2; i++) { //вертикаль
            for (int j = 0; j < mapService.getMap()[i].length; j++) {
                if (mapService.getMap()[i][j] == playerDot && mapService.getMap()[i + 1][j] == playerDot && mapService.getMap()[i + 2][j] == playerDot) {
                    return true;
                }
            }
        }
        for (int i = 0; i < mapService.getMap().length - 2; i++) { //диагональ левый верхний - правый нижний
            for (int j = 0; j < mapService.getMap()[i].length - 2; j++) {
                if (mapService.getMap()[i][j] == playerDot && mapService.getMap()[i + 1][j + 1] == playerDot && mapService.getMap()[i + 2][j + 2] == playerDot) {
                    return true;
                }
            }
        }
        for (int i = 2; i < mapService.getMap().length; i++) { //диагональ левый нижний - правый верхний
            for (int j = 0; j < mapService.getMap()[i].length - 2; j++) {
                if (mapService.getMap()[i][j] == playerDot && mapService.getMap()[i - 1][j + 1] == playerDot && mapService.getMap()[i - 2][j + 2] == playerDot) {
                    return true;
                }
            }
        }
        return false;////////////// нет совпадений
    }

    private void saveProcedure(){
        playerService.savePlayersToFile();
        playerService.baseRefresh();
        playerService.savePlayersToBD();
        xmLout.writeSaveGameFile(getGamePlay(), gameNumber);
        jsoNout.writeSaveGameFile(getGamePlay(), gameNumber);
        gamePlayRepository.save(gamePlay);
    }

}
