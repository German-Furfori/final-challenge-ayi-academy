package com.finalchallenge.app.services.impl;

import com.finalchallenge.app.dto.request.details.DetailsRequestDTO;
import com.finalchallenge.app.dto.request.employee.EmployeeOnlyRequestDTO;
import com.finalchallenge.app.dto.request.employee.EmployeeWithDetailsRequestDTO;
import com.finalchallenge.app.dto.response.details.DetailsResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeeFullDataResponseDTO;
import com.finalchallenge.app.dto.response.employee.EmployeeOnlyResponseDTO;
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

import static com.finalchallenge.app.constants.ExceptionStrings.READ_ACCESS_EXCEPTION_NOT_FOUND;

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
    public EmployeeFullDataResponseDTO addEmployeeWithoutProject(EmployeeWithDetailsRequestDTO employeeWithDetailsRequestDTO) throws RepositoryAccessException {

        utils.verifyEmployeeDni(employeeWithDetailsRequestDTO.getDni());

        EmployeeEntity employeeEntity = employeeMapper.fromDtoFullDataToEntity(employeeWithDetailsRequestDTO);
        employeeEntity.setIsActive(true);

        employeeRepository.save(employeeEntity);

        return employeeMapper.fromEntityToDtoFullData(employeeEntity);

    }

    @Override
    public EmployeeFullDataResponseDTO addEmployeeWithProject(Long idProject, EmployeeWithDetailsRequestDTO employeeWithDetailsRequestDTO) throws RepositoryAccessException {

        utils.verifyEmployeeDni(employeeWithDetailsRequestDTO.getDni());
        ProjectEntity projectEntity = utils.verifyProjectId(idProject);

        EmployeeEntity employeeEntity = employeeMapper.fromDtoFullDataToEntity(employeeWithDetailsRequestDTO);
        employeeEntity.setIsActive(true);
        employeeEntity.setProject(projectEntity);

        employeeRepository.save(employeeEntity);

        return employeeMapper.fromEntityToDtoFullData(employeeEntity);

    }

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
    public EmployeeOnlyResponseDTO modifyEmployeeOnly(Long idEmployee, EmployeeOnlyRequestDTO employeeOnlyRequestDTO) {

        EmployeeEntity employeeEntity = utils.verifyEmployeeId(idEmployee);

        EmployeeEntity employeeEntityUpdated = employeeMapper.fromDtoOnlyToEntity(employeeOnlyRequestDTO);
        employeeEntityUpdated.setIdEmployee(idEmployee);
        employeeEntityUpdated.setProject(employeeEntity.getProject());
        employeeEntityUpdated.setEmployeeDetails(employeeEntity.getEmployeeDetails());
        employeeRepository.save(employeeEntityUpdated);

        return employeeMapper.fromEntityToDtoOnly(employeeEntityUpdated);

    }

    @Override
    public DetailsResponseDTO modifyEmployeeDetails(Long idEmployee, DetailsRequestDTO detailsRequestDTO) {

        EmployeeEntity employeeEntity = utils.verifyEmployeeId(idEmployee);
        employeeEntity.getEmployeeDetails().setSeniority(detailsRequestDTO.getSeniority());
        employeeEntity.getEmployeeDetails().setRole(detailsRequestDTO.getRole());
        employeeEntity.getEmployeeDetails().setSalary(detailsRequestDTO.getSalary());
        employeeRepository.save(employeeEntity);

        return detailsMapper.fromRequestToResponse(detailsRequestDTO);

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
    public EmployeeFullDataResponseDTO removeEmployee(Long idEmployee) throws RepositoryAccessException {

        EmployeeEntity employeeEntity = utils.verifyEmployeeId(idEmployee);

        employeeEntity.setIsActive(false);
        employeeEntity.setEmployeeDetails(null);
        employeeEntity.setProject(null);
        employeeRepository.save(employeeEntity);

        return employeeMapper.fromEntityToDtoFullData(employeeEntity);
    }

}
