package com.game.model;

import com.game.dto.PlayerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String playerName;

    @OneToMany(mappedBy = "player")
    private List<LovedGames> lovedGames = new ArrayList<LovedGames>();

    public static Player toModel(PlayerDTO dto) {
        Player model = new Player();
        model.setPlayerName(dto.getPlayerName());
        return model;
    }

    public static PlayerDTO toDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerName(player.getPlayerName());
        dto.setId(player.getId());
        return dto;
    }
}
