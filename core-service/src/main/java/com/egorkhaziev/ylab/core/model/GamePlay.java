package com.egorkhaziev.ylab.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@Component
@Entity
@Table(name = "gameplay")
public class GamePlay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL,
                mappedBy = "gameplay")
    private List<Player> player;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_result_id")
    private GameResult gameResult;

    public GamePlay() {
        this.game = new Game();
        player = new ArrayList<>();
        gameResult = new GameResult();
        log.debug("gamePlay +");
    }


}
