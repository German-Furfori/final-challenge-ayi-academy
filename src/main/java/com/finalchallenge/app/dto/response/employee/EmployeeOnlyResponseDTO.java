package com.finalchallenge.app.dto.response.employee;

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
        value = "Employee Only Response",
        description = "The employee only data provided by the server"
)
public class EmployeeOnlyResponseDTO {

    @ApiModelProperty(position = 1, notes = "Employee id")
    private Long idEmployee;

    @ApiModelProperty(position = 2, notes = "Employee dni")
    private String dni;

    @ApiModelProperty(position = 3, notes = "Employee first name")
    private String firstName;

    @ApiModelProperty(position = 4, notes = "Employee last name")
    private String lastName;

    @ApiModelProperty(position = 5, notes = "Employee email")
    private String email;

    @ApiModelProperty(position = 6, notes = "Employee activity")
    private Boolean isActive;

}
