package com.egorkhaziev.ylab.core.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Dot {
    private final char EMPTY_DOT = '*';
    private final char X_DOT = 'X';
    private final char O_DOT = 'O';

}
