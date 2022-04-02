package com.egorkhaziev.ylab.core.services;

import com.egorkhaziev.ylab.core.exceptions.UserNotFoundException;
import com.egorkhaziev.ylab.core.logic.model.Player;
import com.egorkhaziev.ylab.core.repositories.PlayerRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.util.*;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class PlayerService implements PlayersStorageInterface{

    private Player player1;
    private Player player2;

    private final PlayerRepository playerRepository;

    private Map<String, Player> playerList = new HashMap<>();

    public Map<String, Player> getPlayerList(){
        return playerList;
    }

    //загрузка пользователей из файла
    public void loadPlayers() {
        playerList = new HashMap<>();
//        //Проверка на наличие файла
//        if(new File("players.txt").exists()) {
//            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("players.txt"))) {
//                playerList = (HashMap) objectInputStream.readObject();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        List<Player> list =  playerRepository.findAll();
        for (Player p:list){
            playerList.put(p.getName(),p);
        }


        log.debug("load player list +");
    }

    public void authorization(String playerName1, String playerName2) {

        player1 = login(playerName1);
        player2 = login(playerName2);
        player1.setId(1);
        player2.setId(2);

        log.debug("auth: " + player1.toString() + " " + player2.toString());

        System.out.println("\nWelcome " + player1.toString() + "\n***********  VS  ***********");
        System.out.println("Welcome " + player2.toString());

    }

    private Player login(String playerName) {
        //проверка базы
        if (playerList.containsKey(playerName)) {
            return playerList.get(playerName);
        }
        //новый юзер, добавление в список.
        Player newPlayer = new Player(playerName);
        playerList.put(playerName, newPlayer);
        return newPlayer;
    }

    @Transactional
    public void baseRefresh() {
        List<Player> activPlayers = new ArrayList<Player>();
        activPlayers.add(player1);
        activPlayers.add(player2);
        playerRepository.saveAll(activPlayers);
    }


    public void savePlayersToFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("players.txt"))) {
            for (Map.Entry<String, Player> entry: getPlayerList().entrySet()) {
                objectOutputStream.writeObject(getPlayerList());
            }
        } catch (IOException ioEx){
            ioEx.getMessage();
        }
    }

    //new сохранение рейтинга
    public void saveLogToFile(Player winPlayer, Player lossPlayer) {
        try (BufferedWriter writter = new BufferedWriter(new FileWriter("rating.txt",true))) {
            writter.write(new Date() + " - Game finished \n");
            writter.write(winPlayer.getName() + " - Winner\n");
            writter.write(lossPlayer.getName() + " - Loss\n");
        }
        catch (IOException ioEx){
            ioEx.getMessage();
        }
    }

    public void savePlayersToBD() {
    }
}
