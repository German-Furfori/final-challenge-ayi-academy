package com.finalchallenge.app.dto.response.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

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

}
