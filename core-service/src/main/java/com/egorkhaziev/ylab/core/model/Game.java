package com.egorkhaziev.ylab.core.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {

    private List<Step> step;

    public Game() {
        this.step = new ArrayList<>();
    }
}
