package com.finalchallenge.app.mappers.impl;

import com.finalchallenge.app.dto.request.employee.EmployeeOnlyRequestDTO;
import com.finalchallenge.app.dto.request.employee.EmployeeWithDetailsRequestDTO;
import com.finalchallenge.app.dto.response.employee.EmployeeFullDataResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeeOnlyResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeePagesResponseDTO;
import com.finalchallenge.app.mappers.IEmployeeMapper;
import com.finalchallenge.app.entities.EmployeeEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeMapperImpl implements IEmployeeMapper {

    private final ModelMapper modelMapper;

    @Override
    public EmployeeEntity fromDtoOnlyToEntity(EmployeeOnlyRequestDTO employeeOnlyRequestDTO) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        modelMapper.map(employeeOnlyRequestDTO, employeeEntity);
        return employeeEntity;
    }

    @Override
    public EmployeeEntity fromDtoFullDataToEntity(EmployeeWithDetailsRequestDTO employeeWithDetailsRequestDTO) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        modelMapper.map(employeeWithDetailsRequestDTO, employeeEntity);

        employeeEntity.getEmployeeDetails().setEmployee(employeeEntity);

        return employeeEntity;
    }

    @Override // Update response
    public EmployeeOnlyResponseDTO fromEntityToDtoOnly(EmployeeEntity employeeEntity) {
        EmployeeOnlyResponseDTO employeeOnlyResponseDTO = new EmployeeOnlyResponseDTO();
        modelMapper.map(employeeEntity, employeeOnlyResponseDTO);
        return employeeOnlyResponseDTO;
    }

    @Override
    public EmployeeFullDataResponseDTO fromEntityToDtoFullData(EmployeeEntity employeeEntity) {
        EmployeeFullDataResponseDTO employeeFullDataResponseDTO = new EmployeeFullDataResponseDTO();
        modelMapper.map(employeeEntity, employeeFullDataResponseDTO);
        return employeeFullDataResponseDTO;
    }

    @Override
    public EmployeePagesResponseDTO fromEntityListToDtoPages(List<EmployeeEntity> employeeEntityList) {
        EmployeePagesResponseDTO employeePagesResponseDTO = new EmployeePagesResponseDTO();
        List<EmployeeFullDataResponseDTO> employeeFullDataResponseDTOList = new ArrayList<>();

        employeeEntityList.forEach(employeeEntity -> {
            EmployeeFullDataResponseDTO employeeFullDataResponseDTO = new EmployeeFullDataResponseDTO();
            modelMapper.map(employeeEntity, employeeFullDataResponseDTO);
            employeeFullDataResponseDTOList.add(employeeFullDataResponseDTO);
        });

        employeePagesResponseDTO.setEmployeeFullDataResponseDTOList(employeeFullDataResponseDTOList);
        return employeePagesResponseDTO;
    }

}
