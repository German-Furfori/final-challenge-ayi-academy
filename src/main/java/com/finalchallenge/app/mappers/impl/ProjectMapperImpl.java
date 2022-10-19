package com.finalchallenge.app.mappers.impl;

import com.finalchallenge.app.mappers.IProjectMapper;
import com.finalchallenge.app.dto.response.project.ProjectResponseDTO;
import com.finalchallenge.app.entities.ProjectEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProjectMapperImpl implements IProjectMapper {

    private final ModelMapper modelMapper;

    @Override
    public ProjectResponseDTO fromEntityToDto(ProjectEntity projectEntity) {
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        modelMapper.map(projectEntity, projectResponseDTO);
        return projectResponseDTO;
    }

}
