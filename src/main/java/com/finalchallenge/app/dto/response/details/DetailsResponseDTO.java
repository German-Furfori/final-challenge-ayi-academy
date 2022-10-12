package com.finalchallenge.app.dto.response.details;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(
        value = "Details Response",
        description = "The employee details data provided by the server"
)
public class DetailsResponseDTO {

    @ApiModelProperty(position = 1, notes = "Employee details ID")
    private Long idDetails;

    @ApiModelProperty(position = 2, notes = "Employee seniority")
    private String seniority;

    @ApiModelProperty(position = 3, notes = "Employee role")
    private String role;

    @ApiModelProperty(position = 4, notes = "Employee salary")
    private Long salary;

}
