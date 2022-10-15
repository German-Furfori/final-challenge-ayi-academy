package com.finalchallenge.app.services;

import com.finalchallenge.app.dto.request.details.DetailsRequestDTO;
import com.finalchallenge.app.dto.request.employee.EmployeeOnlyRequestDTO;
import com.finalchallenge.app.dto.request.employee.EmployeeWithDetailsRequestDTO;
import com.finalchallenge.app.dto.response.details.DetailsResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeeFullDataResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeeOnlyResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeePagesResponseDTO;
import com.finalchallenge.app.exceptions.RepositoryAccessException;

public interface IEmployeeService {

    EmployeePagesResponseDTO findAllClientPages(Integer page, Integer size) throws RepositoryAccessException;

    EmployeeFullDataResponseDTO findEmployeeById(Long idEmployee);

    EmployeeFullDataResponseDTO assignProjectToEmployee(Long idEmployee, Long idProject);

    void incrementSalaries(Double percentage);

    void modifyEmployeeSalary(Long idEmployee, Long salary);
}
