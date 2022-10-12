package com.finalchallenge.app.mappers;

import com.finalchallenge.app.dto.response.project.ProjectPagesResponseDTO;
import com.finalchallenge.app.dto.response.project.ProjectResponseDTO;
import com.finalchallenge.app.entities.ProjectEntity;

import java.util.List;

public interface IProjectMapper {
    ProjectPagesResponseDTO fromEntityListToDtoPages(List<ProjectEntity> projectEntityList);

    ProjectResponseDTO fromEntityToDto(ProjectEntity projectEntity);
}
