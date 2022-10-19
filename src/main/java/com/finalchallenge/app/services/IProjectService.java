package com.finalchallenge.app.services;

import com.finalchallenge.app.dto.response.project.ProjectResponseDTO;
import com.finalchallenge.app.exceptions.RepositoryAccessException;

import java.util.List;

public interface IProjectService {
    List<ProjectResponseDTO> findAllProjectPages() throws RepositoryAccessException;
}
