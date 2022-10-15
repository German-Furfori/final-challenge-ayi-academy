package com.finalchallenge.app.services.impl;

import com.finalchallenge.app.dto.response.employee.EmployeeFullDataResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeePagesResponseDTO;
import com.finalchallenge.app.entities.EmployeeEntity;
import com.finalchallenge.app.entities.ProjectEntity;
import com.finalchallenge.app.exceptions.RepositoryAccessException;
import com.finalchallenge.app.mappers.IDetailsMapper;
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

import static com.finalchallenge.app.constants.ExceptionStrings.READ_ACCESS_EXCEPTION_NOT_FOUND;
import static com.finalchallenge.app.constants.ExceptionStrings.SALARY_INCORRECT;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IEmployeeMapper employeeMapper;

    @Autowired
    private IDetailsMapper detailsMapper;

    private Utils utils;

    @Override
    public EmployeePagesResponseDTO findAllClientPages(Integer page, Integer size) throws RepositoryAccessException {

        EmployeePagesResponseDTO clientFullPagesResponseDTO;
        Pageable pageable = PageRequest.of(page, size);

        Page<EmployeeEntity> employeeEntityPages = employeeRepository.findAll(pageable);

        if(employeeEntityPages != null && !employeeEntityPages.isEmpty()) {
            clientFullPagesResponseDTO = employeeMapper.fromEntityListToDtoPages(employeeEntityPages.getContent());
            clientFullPagesResponseDTO.setSize(employeeEntityPages.getSize());
            clientFullPagesResponseDTO.setCurrentPage(employeeEntityPages.getNumber() + 1);
            clientFullPagesResponseDTO.setTotalPages(employeeEntityPages.getTotalPages());
            clientFullPagesResponseDTO.setTotalElements((int) employeeEntityPages.getTotalElements());
            return clientFullPagesResponseDTO;
        } else {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_NOT_FOUND);
        }

    }

    @Override
    public EmployeeFullDataResponseDTO findEmployeeById(Long idEmployee) {

        EmployeeEntity employeeEntity = utils.verifyEmployeeId(idEmployee);

        return employeeMapper.fromEntityToDtoFullData(employeeEntity);

    }

    @Override
    public EmployeeFullDataResponseDTO assignProjectToEmployee(Long idEmployee, Long idProject) {

        ProjectEntity projectEntity = utils.verifyProjectId(idProject);
        EmployeeEntity employeeEntity = utils.verifyEmployeeId(idEmployee);

        employeeEntity.setProject(projectEntity);
        employeeRepository.save(employeeEntity);

        return employeeMapper.fromEntityToDtoFullData(employeeEntity);

    }

    @Override
    public void incrementSalaries(Double percentage) {

        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();

        if (employeeEntityList == null || employeeEntityList.size() == 0) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_NOT_FOUND);
        }

        employeeEntityList.forEach(employeeEntity -> {
            employeeEntity.getEmployeeDetails().incrementSalary(percentage);
            employeeRepository.save(employeeEntity);
        });

    }

    @Override
    public void modifyEmployeeSalary(Long idEmployee, Long salary) {

        EmployeeEntity employeeEntity = utils.verifyEmployeeId(idEmployee);

        if(salary == null || !(salary instanceof Long)) {
            throw new RepositoryAccessException(SALARY_INCORRECT);
        } else {
            employeeEntity.getEmployeeDetails().setSalary(salary);
            employeeRepository.save(employeeEntity);
        }

    }

}
