package com.finalchallenge.app.services.impl;

import com.finalchallenge.app.dto.response.project.ProjectResponseDTO;
import com.finalchallenge.app.entities.ProjectEntity;
import com.finalchallenge.app.exceptions.RepositoryAccessException;
import com.finalchallenge.app.mappers.IProjectMapper;
import com.finalchallenge.app.repositories.IProjectRepository;
import com.finalchallenge.app.services.IProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.finalchallenge.app.constants.ExceptionStrings.READ_ACCESS_EXCEPTION_NULL_POINTER;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private IProjectMapper projectMapper;

    @Override
    public List<ProjectResponseDTO> findAllProjectPages() throws RepositoryAccessException {

        List<ProjectResponseDTO> projectResponseDTOList = new ArrayList<>();
        List<ProjectEntity> projectEntityList = projectRepository.findAll();

        if( projectEntityList != null ) {
            projectEntityList.forEach(projectEntity -> {
                ProjectResponseDTO projectResponseDTO;
                projectResponseDTO = projectMapper.fromEntityToDto(projectEntity);
                projectResponseDTOList.add(projectResponseDTO);
            });

            return projectResponseDTOList;
        } else {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_NULL_POINTER);
        }

    }

}
