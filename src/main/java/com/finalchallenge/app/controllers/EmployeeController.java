package com.finalchallenge.app.controllers;

import com.finalchallenge.app.dto.response.employee.EmployeeFullDataResponseDTO;
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

// netstat -ano | findstr 8080
// taskkill /F /PID

import java.util.HashMap;
import java.util.Map;

import static com.finalchallenge.app.constants.HashMapStrings.*;

@AllArgsConstructor
@Api(value = "Employee API", tags = {"Employee services"})
@Slf4j
@RequestMapping(value = "/api/employee", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class EmployeeController {

    private IEmployeeService employeeService;

    @GetMapping(
            value = "/getAllEmployeePages/{page}/{size}",
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
            @PathVariable(name = "page") Integer page,
            @ApiParam(value = "Size of the page", required = true, example = "8")
            @PathVariable(name = "size") Integer size) {

        Map<String, Object> response = new HashMap<>();

        EmployeePagesResponseDTO employeePagesResponseDTO;

        try {
            employeePagesResponseDTO = employeeService.findAllClientPages(page - 1, size);
        } catch (RepositoryAccessException e) {
            response.put(CODE, HttpStatus.NOT_FOUND.value());
            response.put(MESSAGE, e.getMessage());
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
            response.put(CODE, HttpStatus.NOT_FOUND.value());
            response.put(MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(employeeFullDataResponseDTO);
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
            response.put(CODE, HttpStatus.NOT_FOUND.value());
            response.put(MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(employeeFullDataResponseDTO);
    }

    @PatchMapping(
            value = "/incrementSalaries/{percentage}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Increment the salary of all employees",
            httpMethod = "PATCH"
    )
    public ResponseEntity<?> incrementSalaries(
            @ApiParam(name = "percentage", required = true, value = "Percentage", example = "10")
            @PathVariable("percentage") Double percentage) {

        Map<String, Object> response = new HashMap<>();

        try {
            employeeService.incrementSalaries(percentage);
            response.put(CODE, HttpStatus.OK.value());
            response.put(MESSAGE, OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RepositoryAccessException e) {
            response.put(CODE, HttpStatus.NOT_FOUND.value());
            response.put(MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @PatchMapping(
            value = "/updateEmployeeSalary/{idEmployee}/{salary}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(
            value = "Modifies the employee salary",
            httpMethod = "PATCH"
    )
    public ResponseEntity<?> updateEmployeeSalary(
            @ApiParam(name = "idEmployee", required = true, value = "Employee ID", example = "1")
            @PathVariable("idEmployee") Long idEmployee,
            @ApiParam(name = "salary", required = true, value = "New salary", example = "100000")
            @PathVariable("salary") Long salary) {

        Map<String, Object> response = new HashMap<>();

        try {
            employeeService.modifyEmployeeSalary(idEmployee, salary);
            response.put(CODE, HttpStatus.OK.value());
            response.put(MESSAGE, OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RepositoryAccessException e) {
            response.put(CODE, HttpStatus.NOT_FOUND.value());
            response.put(MESSAGE, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

}
