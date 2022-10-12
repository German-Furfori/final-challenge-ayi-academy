package com.finalchallenge.app.mappers.impl;

import com.finalchallenge.app.mappers.IProjectMapper;
import com.finalchallenge.app.dto.response.project.ProjectPagesResponseDTO;
import com.finalchallenge.app.dto.response.project.ProjectResponseDTO;
import com.finalchallenge.app.entities.ProjectEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ProjectMapperImpl implements IProjectMapper {

    private final ModelMapper modelMapper;

    @Override
    public ProjectPagesResponseDTO fromEntityListToDtoPages(List<ProjectEntity> projectEntityList) {
        ProjectPagesResponseDTO projectPagesResponseDTO = new ProjectPagesResponseDTO();
        List<ProjectResponseDTO> projectResponseDTOList = new ArrayList<>();

        projectEntityList.forEach(projectEntity -> {
            ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
            modelMapper.map(projectEntity, projectResponseDTO);
            projectResponseDTOList.add(projectResponseDTO);
        });

        projectPagesResponseDTO.setProjectResponseDTOList(projectResponseDTOList);
        return projectPagesResponseDTO;
    }

    @Override
    public ProjectResponseDTO fromEntityToDto(ProjectEntity projectEntity) {
        ProjectResponseDTO projectResponseDTO = new ProjectResponseDTO();
        modelMapper.map(projectEntity, projectResponseDTO);
        return projectResponseDTO;
    }

}
