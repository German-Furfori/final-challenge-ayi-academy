package com.finalchallenge.app.mappers;

import com.finalchallenge.app.dto.response.project.ProjectResponseDTO;
import com.finalchallenge.app.entities.ProjectEntity;

public interface IProjectMapper {

    ProjectResponseDTO fromEntityToDto(ProjectEntity projectEntity);
}
