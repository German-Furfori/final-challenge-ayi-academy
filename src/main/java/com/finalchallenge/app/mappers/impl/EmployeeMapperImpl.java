package com.finalchallenge.app.mappers.impl;

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
    public EmployeeFullDataResponseDTO fromEntityToDtoFullData(EmployeeEntity employeeEntity) {
        EmployeeFullDataResponseDTO employeeFullDataResponseDTO = new EmployeeFullDataResponseDTO();
        modelMapper.map(employeeEntity, employeeFullDataResponseDTO);
        return employeeFullDataResponseDTO;
    }

    @Override
    public EmployeePagesResponseDTO fromEntityListToDtoPages(List<EmployeeEntity> employeeEntityList) {
        EmployeePagesResponseDTO employeePagesResponseDTO = new EmployeePagesResponseDTO();
        List<EmployeeOnlyResponseDTO> employeeOnlyResponseDTOList = new ArrayList<>();

        employeeEntityList.forEach(employeeEntity -> {
            EmployeeOnlyResponseDTO employeeOnlyResponseDTO = new EmployeeOnlyResponseDTO();
            modelMapper.map(employeeEntity, employeeOnlyResponseDTO);
            employeeOnlyResponseDTOList.add(employeeOnlyResponseDTO);
        });

        employeePagesResponseDTO.setEmployeeOnlyResponseDTOList(employeeOnlyResponseDTOList);
        return employeePagesResponseDTO;
    }

}
