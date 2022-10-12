package com.finalchallenge.app.controllers;

import com.finalchallenge.app.dto.request.details.DetailsRequestDTO;
import com.finalchallenge.app.dto.request.employee.EmployeeOnlyRequestDTO;
import com.finalchallenge.app.dto.request.employee.EmployeeWithDetailsRequestDTO;
import com.finalchallenge.app.dto.response.details.DetailsResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeeFullDataResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeeOnlyResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeePagesResponseDTO;
import com.finalchallenge.app.exceptions.RepositoryAccessException;
import com.finalchallenge.app.services.IEmployeeService;
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
import static com.finalchallenge.app.constants.IntegerConstants.PAGE_SIZE;

@AllArgsConstructor
@Api(value = "Employee API", tags = {"Employee services"})
@Slf4j
@RequestMapping(value = "/employee", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class EmployeeController {

    private IEmployeeService employeeService;

    @PostMapping(value = "/createClientWithoutProject")
    @ApiOperation(
            value = "Adds an employee to the DB table without a project",
            httpMethod = "POST",
            response = EmployeeFullDataResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Body content with the new employee"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received"
            )
    })
    public ResponseEntity<?> createEmployeeWithoutProject(@Valid @RequestBody EmployeeWithDetailsRequestDTO employeeWithDetailsRequestDTO) {

        Map<String, Object> response = new HashMap<>();

        EmployeeFullDataResponseDTO employeeFullDataResponseDTO;

        try {
            employeeFullDataResponseDTO = employeeService.addEmployeeWithoutProject(employeeWithDetailsRequestDTO);
        } catch (RepositoryAccessException e) {
            response.put(ERROR_CODE, HttpStatus.NOT_ACCEPTABLE.value());
            response.put(ERROR_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(employeeFullDataResponseDTO);
    }

    @PostMapping(value = "/createClientWithProject/{idProject}")
    @ApiOperation(
            value = "Adds an employee to the DB table with a project assigned",
            httpMethod = "POST",
            response = EmployeeFullDataResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Body content with the new employee"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received"
            )
    })
    public ResponseEntity<?> createEmployeeWithProject(
            @ApiParam(name = "idProject", required = true, value = "Project Id", example = "1")
            @PathVariable("idProject") Long idProject,
            @Valid @RequestBody EmployeeWithDetailsRequestDTO employeeWithDetailsRequestDTO) {

        Map<String, Object> response = new HashMap<>();

        EmployeeFullDataResponseDTO employeeFullDataResponseDTO;

        try {
            employeeFullDataResponseDTO = employeeService.addEmployeeWithProject(idProject, employeeWithDetailsRequestDTO);
        } catch (RepositoryAccessException e) {
            response.put(ERROR_CODE, HttpStatus.NOT_ACCEPTABLE.value());
            response.put(ERROR_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(employeeFullDataResponseDTO);
    }

    @GetMapping(
            value = "/getAllEmployeePages/{page}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Retrieves data associated to all the employees paginated",
            httpMethod = "GET",
            response = EmployeePagesResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with the employees information"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received")
    })
    public ResponseEntity<?> getAllEmployeePages(
            @ApiParam(value = "Page to display", required = true, example = "1")
            @PathVariable(name = "page") Integer page) {

        Map<String, Object> response = new HashMap<>();

        EmployeePagesResponseDTO employeePagesResponseDTO;

        try {
            employeePagesResponseDTO = employeeService.findAllClientPages(page - 1, PAGE_SIZE);
        } catch (RepositoryAccessException e) {
            response.put(ERROR_CODE, HttpStatus.NOT_FOUND.value());
            response.put(ERROR_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(employeePagesResponseDTO);
    }

    @GetMapping(
            value = "/getEmployeeById/{idEmployee}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Retrieves data associated to the employee by Id",
            httpMethod = "GET",
            response = EmployeeFullDataResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with the employee information"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received")
    })
    public ResponseEntity<?> getEmployeeById(
            @ApiParam(name = "idEmployee", required = true, value = "Employee Id", example = "1")
            @PathVariable("idEmployee") Long idEmployee) {

        Map<String, Object> response = new HashMap<>();

        EmployeeFullDataResponseDTO employeeFullDataResponseDTO;

        try {
            employeeFullDataResponseDTO = employeeService.findEmployeeById(idEmployee);
        } catch (RepositoryAccessException e) {
            response.put(ERROR_CODE, HttpStatus.NOT_FOUND.value());
            response.put(ERROR_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(employeeFullDataResponseDTO);
    }

    @PutMapping(
            value = "/updateEmployeeOnly/{idEmployee}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Retrieves data associated to the employee updated",
            httpMethod = "PUT",
            response = EmployeeOnlyResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Body content with the employee updated"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received")
    })
    public ResponseEntity<?> updateEmployeeOnly(
            @ApiParam(name = "idEmployee", required = true, value = "Employee Id", example = "1")
            @PathVariable("idEmployee") Long idEmployee,
            @Valid @RequestBody EmployeeOnlyRequestDTO employeeOnlyRequestDTO) {

        Map<String, Object> response = new HashMap<>();

        EmployeeOnlyResponseDTO employeeOnlyResponseDTO;

        try {
            employeeOnlyResponseDTO = employeeService.modifyEmployeeOnly(idEmployee, employeeOnlyRequestDTO);
        } catch (RepositoryAccessException e) {
            response.put(ERROR_CODE, HttpStatus.NOT_FOUND.value());
            response.put(ERROR_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(employeeOnlyResponseDTO);
    }

    @PutMapping(
            value = "/updateEmployeeDetails/{idEmployee}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Retrieves data associated to the employee details updated",
            httpMethod = "PUT",
            response = DetailsResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Body content with the employee details updated"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received")
    })
    public ResponseEntity<?> updateEmployeeDetails(
            @ApiParam(name = "idEmployee", required = true, value = "Employee Id", example = "1")
            @PathVariable("idEmployee") Long idEmployee,
            @Valid @RequestBody DetailsRequestDTO detailsRequestDTO) {

        Map<String, Object> response = new HashMap<>();

        DetailsResponseDTO detailsResponseDTO;

        try {
            detailsResponseDTO = employeeService.modifyEmployeeDetails(idEmployee, detailsRequestDTO);
        } catch (RepositoryAccessException e) {
            response.put(ERROR_CODE, HttpStatus.NOT_FOUND.value());
            response.put(ERROR_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(detailsResponseDTO);
    }

    @PatchMapping(
            value = "/assignProjectToEmployee/{idEmployee}/{idProject}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Retrieves data associated to the employee assigned project",
            httpMethod = "PATCH",
            response = EmployeeFullDataResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Body content with the employee assigned project"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received")
    })
    public ResponseEntity<?> assignProjectToEmployee(
            @ApiParam(name = "idEmployee", required = true, value = "Employee Id", example = "1")
            @PathVariable("idEmployee") Long idEmployee,
            @ApiParam(name = "idProject", required = true, value = "Project Id", example = "1")
            @PathVariable("idProject") Long idProject) {

        Map<String, Object> response = new HashMap<>();

        EmployeeFullDataResponseDTO employeeFullDataResponseDTO;

        try {
            employeeFullDataResponseDTO = employeeService.assignProjectToEmployee(idEmployee, idProject);
        } catch (RepositoryAccessException e) {
            response.put(ERROR_CODE, HttpStatus.NOT_FOUND.value());
            response.put(ERROR_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(employeeFullDataResponseDTO);
    }

    @PatchMapping(
            value = "/fireEmployee/{idEmployee}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Retrieves data associated to the fired employe",
            httpMethod = "PATCH",
            response = EmployeeFullDataResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Body content with the fired employee"
            ),
            @ApiResponse(
                    code = 400,
                    message = "Describes errors on invalid payload received")
    })
    public ResponseEntity<?> fireEmployee(
            @ApiParam(name = "idEmployee", required = true, value = "Employee Id", example = "1")
            @PathVariable("idEmployee") Long idEmployee) {

        Map<String, Object> response = new HashMap<>();

        EmployeeFullDataResponseDTO employeeFullDataResponseDTO;

        try {
            employeeFullDataResponseDTO = employeeService.removeEmployee(idEmployee);
        } catch (RepositoryAccessException e) {
            response.put(ERROR_CODE, HttpStatus.NOT_FOUND.value());
            response.put(ERROR_MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(employeeFullDataResponseDTO);

    }

}
