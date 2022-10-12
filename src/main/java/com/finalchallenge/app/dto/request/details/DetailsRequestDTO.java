package com.finalchallenge.app.dto.request.details;

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
        value = "Details Request",
        description = "Data needed to create or update the employee details"
)
public class DetailsRequestDTO {

    @NotNull(message = "Seniority information cannot be null")
    @ApiModelProperty(position = 1, required = true)
    private String seniority;

    @NotNull(message = "Role cannot be null")
    @ApiModelProperty(position = 2, required = true)
    private String role;

    @NotNull(message = "Salary cannot be null")
    @ApiModelProperty(position = 3, required = true)
    private Long salary;

}
