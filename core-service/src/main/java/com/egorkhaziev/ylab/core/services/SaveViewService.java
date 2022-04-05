package com.egorkhaziev.ylab.core.services;

import com.egorkhaziev.ylab.core.model.GamePlay;
import com.egorkhaziev.ylab.core.model.Player;
import com.egorkhaziev.ylab.core.model.Step;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Thread.sleep;

@RequiredArgsConstructor
@Service
public class SaveViewService implements SaveViewInterface {

    private final MapJobInterface mapJobService;

    public void playSaveGame(GamePlay gamePlay){

        List<Step> steps = gamePlay.getGame().getStep();
        String winner = null;
        List<Player> players = gamePlay.getPlayer();

        if (gamePlay.getGameResult().getWinner()!=null) {
            winner = gamePlay.getGameResult().getWinner();
        }

        System.out.println(players.get(0).getName() + " VS " + players.get(1).getName());
        mapJobService.initMap();
        mapJobService.paintMap();

        for (int i = 0; i <= steps.size()-1; i++) {

            int x = gamePlay.getGame().getStep().get(i).getX();
            int y = gamePlay.getGame().getStep().get(i).getY();
            mapJobService.getMap()[x - 1][y - 1] = ((i % 2 == 1) ? players.get(1).getSimbol() : players.get(0).getSimbol());
            mapJobService.paintMap();
            System.out.println("step "+((i % 2 == 1) ? players.get(1).getName() : players.get(0).getName()));
            sleeping(1000);
        }

        if (winner!=null) {
            System.out.println("*** WINNER: " + winner + " ***");
        } else {
            System.out.println("*** DRAW! ***");
        }
        sleeping(2500);
    }

    private void sleeping(int ms){
        try {
            sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
