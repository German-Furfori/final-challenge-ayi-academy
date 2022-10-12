package com.finalchallenge.app.utils;

import com.finalchallenge.app.entities.DetailsEntity;
import com.finalchallenge.app.entities.EmployeeEntity;
import com.finalchallenge.app.entities.ProjectEntity;
import com.finalchallenge.app.exceptions.RepositoryAccessException;
import com.finalchallenge.app.repositories.IDetailsRepository;
import com.finalchallenge.app.repositories.IEmployeeRepository;
import com.finalchallenge.app.repositories.IProjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.finalchallenge.app.constants.ExceptionStrings.*;

@Service
@AllArgsConstructor
public class Utils {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IDetailsRepository detailsRepository;

    @Autowired
    private IProjectRepository projectRepository;

    /**
     *
     * Function to verify the integrity or existence of the employee ID provided
     *
     * */
    public EmployeeEntity verifyEmployeeId(Long idEmployee) throws RepositoryAccessException {
        if(idEmployee == null || idEmployee <= 0) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_INCORRECT_INPUT);
        }

        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(idEmployee);

        if(!employeeEntity.isPresent()) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_EMPLOYEE_ID_NOT_FOUND);
        }

        return employeeEntity.get();
    }

    /**
     *
     * Function to verify the integrity or existence of the project ID provided
     *
     * */
    public ProjectEntity verifyProjectId(Long idProject) throws RepositoryAccessException {
        if(idProject == null || idProject <= 0) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_INCORRECT_INPUT);
        }

        Optional<ProjectEntity> projectEntity = projectRepository.findById(idProject);

        if(!projectEntity.isPresent()) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_PROJECT_ID_NOT_FOUND);
        }

        return projectEntity.get();
    }

    /**
     *
     * Function to verify the integrity or existence of the employee details ID provided
     *
     * */
    public DetailsEntity verifyDetailsId(Long idDetails) throws RepositoryAccessException {
        if(idDetails == null || idDetails <= 0) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_INCORRECT_INPUT);
        }

        Optional<DetailsEntity> detailsEntity = detailsRepository.findById(idDetails);

        if(!detailsEntity.isPresent()) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_DETAILS_ID_NOT_FOUND);
        }

        return detailsEntity.get();
    }

    /**
     *
     * Function to verify existence of the DNI provided
     *
     * */
    public void verifyEmployeeDni(String dni) throws RepositoryAccessException {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findByDni(dni);

        if(employeeEntity.isPresent()) {
            throw new RepositoryAccessException(WRITE_ACCESS_EXCEPTION_DNI);
        }
    }

}
