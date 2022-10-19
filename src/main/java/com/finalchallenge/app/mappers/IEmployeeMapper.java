package com.finalchallenge.app.mappers;

import com.finalchallenge.app.dto.response.employee.EmployeeFullDataResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeePagesResponseDTO;
import com.finalchallenge.app.entities.EmployeeEntity;

import java.util.List;

public interface IEmployeeMapper {

    EmployeeFullDataResponseDTO fromEntityToDtoFullData(EmployeeEntity employeeEntity);

    EmployeePagesResponseDTO fromEntityListToDtoPages(List<EmployeeEntity> employeeEntityList);
}
