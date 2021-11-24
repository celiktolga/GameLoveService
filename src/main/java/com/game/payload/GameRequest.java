package com.game.payload;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameRequest {
    @ApiModelProperty(name = "name", dataType = "String", value = "name", example = "gamer")
    @NotEmpty
    private String name;
}
