package com.finalchallenge.app.controllers;

import com.finalchallenge.app.dto.response.employee.EmployeePagesResponseDTO;
import com.finalchallenge.app.dto.response.project.ProjectResponseDTO;
import com.finalchallenge.app.exceptions.RepositoryAccessException;
import com.finalchallenge.app.services.IProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.finalchallenge.app.constants.HashMapStrings.CODE;
import static com.finalchallenge.app.constants.HashMapStrings.MESSAGE;

@AllArgsConstructor
@Api(value = "Project API", tags = {"Project services"})
@Slf4j
@RequestMapping(value = "/api/project", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class ProjectController {

    IProjectService projectService;

    @GetMapping(
            value = "/getAllProjects",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Retrieves data associated to all the projects",
            httpMethod = "GET",
            response = EmployeePagesResponseDTO.class
    )
    public ResponseEntity<?> getAllProjects() {

        Map<String, Object> response = new HashMap<>();

        List<ProjectResponseDTO> projectResponseDTOList;

        try {
            projectResponseDTOList = projectService.findAllProjectPages();
        } catch (RepositoryAccessException e) {
            response.put(CODE, HttpStatus.NOT_FOUND.value());
            response.put(MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(projectResponseDTOList);
    }

}
