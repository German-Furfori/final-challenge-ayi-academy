package com.finalchallenge.app.services;

import com.finalchallenge.app.dto.response.employee.EmployeeFullDataResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeePagesResponseDTO;
import com.finalchallenge.app.exceptions.RepositoryAccessException;

public interface IEmployeeService {

    EmployeePagesResponseDTO findAllEmployeePages(Integer page, Integer size) throws RepositoryAccessException;

    EmployeeFullDataResponseDTO findEmployeeById(Long idEmployee);

    void assignProjectToEmployee(Long idEmployee, Long idProject);

    void incrementSalaries(Double percentage);

    void modifyEmployeeSalary(Long idEmployee, Long salary);
}
