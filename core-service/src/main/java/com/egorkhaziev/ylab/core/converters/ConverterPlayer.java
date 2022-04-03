package com.egorkhaziev.ylab.core.converters;

import com.egorkhaziev.ylab.core.DTO.PlayerDTO;
import com.egorkhaziev.ylab.core.model.Player;
import com.egorkhaziev.ylab.core.services.PlayersStorageInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class ConverterPlayer {

    private final PlayersStorageInterface players;

    public PlayerDTO playerToDTO(Player player){
        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setSimbol(player.getSimbol());
        return dto;
    }

    public Player dtoToPlayer(PlayerDTO dto){
        Player player = players.getPlayerList().get(dto.getName());
        return player;
    }

}
