package com.egorkhaziev.ylab.core.services;

public interface MapJobInterface {
    void initMap();
    void paintMap();
    char[][] getMap();
    void mapCountDownMinus();
    int getMapCountDown();
}
