package com.game.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PlayerDTO {
    private Long id;
    @ApiModelProperty(name = "playerName", dataType = "String", value = "playerName", example = "Player")
    private String playerName;
}
