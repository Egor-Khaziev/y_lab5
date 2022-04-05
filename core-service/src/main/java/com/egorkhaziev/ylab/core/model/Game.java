package com.egorkhaziev.ylab.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "game")
    private List<Step> step;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gameresult_id")
    private GameResult gameResult;

    public Game() {
        this.step = new ArrayList<>();
    }
}
