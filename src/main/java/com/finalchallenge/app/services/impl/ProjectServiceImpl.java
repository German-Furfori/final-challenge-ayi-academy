package com.finalchallenge.app.services.impl;

import com.finalchallenge.app.dto.response.project.ProjectPagesResponseDTO;
import com.finalchallenge.app.dto.response.project.ProjectResponseDTO;
import com.finalchallenge.app.entities.ProjectEntity;
import com.finalchallenge.app.exceptions.RepositoryAccessException;
import com.finalchallenge.app.mappers.IProjectMapper;
import com.finalchallenge.app.repositories.IProjectRepository;
import com.finalchallenge.app.services.IProjectService;
import com.finalchallenge.app.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.finalchallenge.app.constants.ExceptionStrings.READ_ACCESS_EXCEPTION_NOT_FOUND;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private IProjectMapper projectMapper;

    private Utils utils;

    @Override
    public ProjectPagesResponseDTO findAllProjectPages(Integer page, Integer size) throws RepositoryAccessException {

        ProjectPagesResponseDTO projectPagesResponseDTO;
        Pageable pageable = PageRequest.of(page, size);

        Page<ProjectEntity> projectEntityPages = projectRepository.findAll(pageable);

        if(projectEntityPages != null && !projectEntityPages.isEmpty()) {
            projectPagesResponseDTO = projectMapper.fromEntityListToDtoPages(projectEntityPages.getContent());
            projectPagesResponseDTO.setSize(projectEntityPages.getSize());
            projectPagesResponseDTO.setCurrentPage(projectEntityPages.getNumber() + 1);
            projectPagesResponseDTO.setTotalPages(projectEntityPages.getTotalPages());
            projectPagesResponseDTO.setTotalElements((int) projectEntityPages.getTotalElements());
            return projectPagesResponseDTO;
        } else {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_NOT_FOUND);
        }

    }

    @Override
    public ProjectResponseDTO findProjectById(Long idProject) {

        ProjectEntity projectEntity = utils.verifyProjectId(idProject);

        return projectMapper.fromEntityToDto(projectEntity);

    }

}
