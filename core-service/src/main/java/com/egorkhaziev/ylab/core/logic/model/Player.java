package com.egorkhaziev.ylab.core.logic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player implements Serializable {

    private int id;

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "win")
    @JsonIgnore
    private int win;

    @Column(name = "loss")
    @JsonIgnore
    private int loss;

    @Column(name = "seriesWin")
    @JsonIgnore
    private int seriesWin;

    private char simbol;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Player(String name) {
        this.name = name;

    }

    @Override
    public String toString() {
        return  name + " You win: " + win + " and loss: " + loss +
                "\nYour series of victories: " + seriesWin;
    }
}
