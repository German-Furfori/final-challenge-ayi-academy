package com.finalchallenge.app.dto.response.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finalchallenge.app.dto.response.employee.EmployeeFullDataResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeeOnlyResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ApiModel(
        value = "Project Response",
        description = "The project data provided by the server"
)
public class ProjectResponseDTO {

    @ApiModelProperty(position = 1, notes = "Project ID")
    private Long idProject;

    @ApiModelProperty(position = 2, notes = "Project customer")
    private String customer;

    @ApiModelProperty(position = 3, notes = "Project technologies")
    private String technologies;

    @ApiModelProperty(position = 4, notes = "Project limit date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate limitDate;

    @ApiModelProperty(position = 5, notes = "Project employee list")
    @JsonIgnoreProperties(value = "project")
    private List<EmployeeOnlyResponseDTO> employeeList;

}
