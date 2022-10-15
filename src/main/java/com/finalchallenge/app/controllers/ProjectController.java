package com.finalchallenge.app.controllers;

import com.finalchallenge.app.dto.response.employee.EmployeeFullDataResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeePagesResponseDTO;
import com.finalchallenge.app.dto.response.project.ProjectPagesResponseDTO;
import com.finalchallenge.app.dto.response.project.ProjectResponseDTO;
import com.finalchallenge.app.exceptions.RepositoryAccessException;
import com.finalchallenge.app.services.IProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.finalchallenge.app.constants.HashMapStrings.ERROR_CODE;
import static com.finalchallenge.app.constants.HashMapStrings.ERROR_MESSAGE;

@AllArgsConstructor
@Api(value = "Project API", tags = {"Project services"})
@Slf4j
@RequestMapping(value = "/api/project", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class ProjectController {

    IProjectService projectService;

    @GetMapping(
            value = "/getAllProjectPages/{page}/{size}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Retrieves data associated to all the projects paginated",
            httpMethod = "GET",
            response = EmployeePagesResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with the projects information"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received")
    })
    public ResponseEntity<?> getAllProjectPages(
            @ApiParam(value = "Page to display", required = true, example = "1")
            @PathVariable(name = "page") Integer page,
            @ApiParam(value = "Size of the page", required = true, example = "8")
            @PathVariable(name = "size") Integer size) {

        Map<String, Object> response = new HashMap<>();

        ProjectPagesResponseDTO projectPagesResponseDTO;

        try {
            projectPagesResponseDTO = projectService.findAllProjectPages(page - 1, size);
        } catch (RepositoryAccessException e) {
            response.put(ERROR_CODE, HttpStatus.NOT_FOUND.value());
            response.put(ERROR_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(projectPagesResponseDTO);
    }

    @GetMapping(
            value = "/getProjectById/{idProject}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Retrieves data associated to the project by Id",
            httpMethod = "GET",
            response = EmployeeFullDataResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with the project information"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received")
    })
    public ResponseEntity<?> getProjectById(
            @ApiParam(name = "idProject", required = true, value = "Project Id", example = "1")
            @PathVariable("idProject") Long idProject) {

        Map<String, Object> response = new HashMap<>();

        ProjectResponseDTO projectResponseDTO;

        try {
            projectResponseDTO = projectService.findProjectById(idProject);
        } catch (RepositoryAccessException e) {
            response.put(ERROR_CODE, HttpStatus.NOT_FOUND.value());
            response.put(ERROR_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(projectResponseDTO);
    }

}
