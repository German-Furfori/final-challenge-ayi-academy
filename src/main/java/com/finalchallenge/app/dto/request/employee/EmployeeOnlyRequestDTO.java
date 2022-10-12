package com.finalchallenge.app.dto.request.employee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(
        value = "Employee Request",
        description = "Data needed to update the employee"
)
public class EmployeeOnlyRequestDTO {

    @NotNull(message = "DNI cannot be null")
    @ApiModelProperty(position = 1, required = true)
    private String dni;

    @NotNull(message = "First name cannot be null")
    @ApiModelProperty(position = 2, required = true)
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @ApiModelProperty(position = 3, required = true)
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @ApiModelProperty(position = 4, required = true)
    private String email;

    @NotNull(message = "Employee activity information cannot be null")
    @ApiModelProperty(position = 5, required = true)
    private Boolean isActive;

}
