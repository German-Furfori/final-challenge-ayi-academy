package com.finalchallenge.app.dto.response.employee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finalchallenge.app.dto.response.details.DetailsResponseDTO;
import com.finalchallenge.app.dto.response.project.ProjectResponseDTO;
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
        value = "Employee Full Data Response",
        description = "The employee full data provided by the server"
)
public class EmployeeFullDataResponseDTO {

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

    @ApiModelProperty(position = 7, notes = "Employee details")
    private DetailsResponseDTO employeeDetails;

    @ApiModelProperty(position = 8, notes = "Assigned project")
    @JsonIgnoreProperties(value = "employeeList")
    private ProjectResponseDTO project;

}
