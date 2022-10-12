package com.finalchallenge.app.services;

import com.finalchallenge.app.dto.response.project.ProjectPagesResponseDTO;
import com.finalchallenge.app.dto.response.project.ProjectResponseDTO;
import com.finalchallenge.app.exceptions.RepositoryAccessException;

public interface IProjectService {
    ProjectPagesResponseDTO findAllProjectPages(Integer page, Integer size) throws RepositoryAccessException;

    ProjectResponseDTO findProjectById(Long idProject);
}
