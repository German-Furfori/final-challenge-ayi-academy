package com.finalchallenge.app.dto.response.employee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(
        value = "Employee Pages Response DTO",
        description = "Represents the pagination of the employee data"
)
public class EmployeePagesResponseDTO {

    @ApiModelProperty(position = 1, notes = "Employee list")
    private List<EmployeeFullDataResponseDTO> employeeFullDataResponseDTOList;

    @ApiModelProperty(position = 2, notes = "Total pages")
    private Integer totalPages;

    @ApiModelProperty(position = 3, notes = "Current page")
    private Integer currentPage;

    @ApiModelProperty(position = 4, notes = "Size")
    private Integer size;

    @ApiModelProperty(position = 5, notes = "Total number of page elements")
    private Integer totalElements;

}
