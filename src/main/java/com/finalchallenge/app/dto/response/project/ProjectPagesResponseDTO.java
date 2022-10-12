package com.finalchallenge.app.dto.response.project;

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
        value = "Project Pages Response DTO",
        description = "Represents the pagination of the project data"
)
public class ProjectPagesResponseDTO {

    @ApiModelProperty(position = 1, notes = "Project list")
    private List<ProjectResponseDTO> projectResponseDTOList;

    @ApiModelProperty(position = 2, notes = "Total pages")
    private Integer totalPages;

    @ApiModelProperty(position = 3, notes = "Current page")
    private Integer currentPage;

    @ApiModelProperty(position = 4, notes = "Size")
    private Integer size;

    @ApiModelProperty(position = 5, notes = "Total number of page elements")
    private Integer totalElements;

}
