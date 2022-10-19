package com.finalchallenge.app.services.impl;

import com.finalchallenge.app.dto.response.employee.EmployeeFullDataResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeePagesResponseDTO;
import com.finalchallenge.app.entities.EmployeeEntity;
import com.finalchallenge.app.entities.ProjectEntity;
import com.finalchallenge.app.exceptions.RepositoryAccessException;
import com.finalchallenge.app.mappers.IEmployeeMapper;
import com.finalchallenge.app.repositories.IEmployeeRepository;
import com.finalchallenge.app.services.IEmployeeService;
import com.finalchallenge.app.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.finalchallenge.app.constants.ExceptionStrings.*;
import static com.finalchallenge.app.constants.LongConstants.MAXIMUM_PERCENTAGE;
import static com.finalchallenge.app.constants.LongConstants.MINIMUM_SALARY;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IEmployeeMapper employeeMapper;

    private Utils utils;

    @Override
    public EmployeePagesResponseDTO findAllEmployeePages(Integer page, Integer size) throws RepositoryAccessException {

        utils.verifyPageAndSize(page, size);

        EmployeePagesResponseDTO employeePagesResponseDTO;
        Pageable pageable = PageRequest.of(page, size);

        Page<EmployeeEntity> employeeEntityPages = employeeRepository.findAll(pageable);

        if( employeeEntityPages != null ) {
            employeePagesResponseDTO = employeeMapper.fromEntityListToDtoPages(employeeEntityPages.getContent());
            employeePagesResponseDTO.setSize(employeeEntityPages.getSize());
            employeePagesResponseDTO.setCurrentPage(employeeEntityPages.getNumber() + 1);
            employeePagesResponseDTO.setTotalPages(employeeEntityPages.getTotalPages());
            employeePagesResponseDTO.setTotalElements((int) employeeEntityPages.getTotalElements());

            return employeePagesResponseDTO;
        } else {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_NULL_POINTER);
        }


    }

    @Override
    public EmployeeFullDataResponseDTO findEmployeeById(Long idEmployee) {

        EmployeeEntity employeeEntity = utils.verifyEmployeeId(idEmployee);

        return employeeMapper.fromEntityToDtoFullData(employeeEntity);

    }

    @Override
    public void assignProjectToEmployee(Long idEmployee, Long idProject) {

        ProjectEntity projectEntity = utils.verifyProjectId(idProject);
        EmployeeEntity employeeEntity = utils.verifyEmployeeId(idEmployee);

        employeeEntity.setProject(projectEntity);
        employeeRepository.save(employeeEntity);

    }

    @Override
    public void incrementSalaries(Double percentage) throws RepositoryAccessException {

        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();

        if(percentage == null || !(percentage instanceof Double) || percentage > MAXIMUM_PERCENTAGE || percentage <= 0) {
            throw new RepositoryAccessException(PERCENTAGE_INCORRECT);
        } else {
            employeeEntityList.forEach(employeeEntity -> {
                employeeEntity.getEmployeeDetails().incrementSalary(percentage);
                employeeRepository.save(employeeEntity);
            });
        }

    }

    @Override
    public void modifyEmployeeSalary(Long idEmployee, Long salary) throws RepositoryAccessException {

        EmployeeEntity employeeEntity = utils.verifyEmployeeId(idEmployee);

        if(salary == null || !(salary instanceof Long) || salary < MINIMUM_SALARY) { // The minimum salary of the company is 100000
            throw new RepositoryAccessException(SALARY_INCORRECT);
        } else {
            employeeEntity.getEmployeeDetails().setSalary(salary);
            employeeRepository.save(employeeEntity);
        }

    }

}
