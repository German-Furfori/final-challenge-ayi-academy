package com.finalchallenge.app.mappers;

import com.finalchallenge.app.dto.request.employee.EmployeeOnlyRequestDTO;
import com.finalchallenge.app.dto.request.employee.EmployeeWithDetailsRequestDTO;
import com.finalchallenge.app.dto.response.employee.EmployeeFullDataResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeeOnlyResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeePagesResponseDTO;
import com.finalchallenge.app.entities.EmployeeEntity;

import java.util.List;

public interface IEmployeeMapper {
    EmployeeEntity fromDtoOnlyToEntity(EmployeeOnlyRequestDTO employeeOnlyRequestDTO);

    EmployeeEntity fromDtoFullDataToEntity(EmployeeWithDetailsRequestDTO employeeWithDetailsRequestDTO);

    EmployeeOnlyResponseDTO fromEntityToDtoOnly(EmployeeEntity employeeEntity);

    EmployeeFullDataResponseDTO fromEntityToDtoFullData(EmployeeEntity employeeEntity);

    EmployeePagesResponseDTO fromEntityListToDtoPages(List<EmployeeEntity> employeeEntityList);
}
