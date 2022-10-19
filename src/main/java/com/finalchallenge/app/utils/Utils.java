package com.finalchallenge.app.utils;

import com.finalchallenge.app.entities.EmployeeEntity;
import com.finalchallenge.app.entities.ProjectEntity;
import com.finalchallenge.app.exceptions.PaginationException;
import com.finalchallenge.app.exceptions.RepositoryAccessException;
import com.finalchallenge.app.repositories.IEmployeeRepository;
import com.finalchallenge.app.repositories.IProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.finalchallenge.app.constants.ExceptionStrings.*;

@Service
@AllArgsConstructor
public class Utils {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private IProjectRepository projectRepository;

    /**
     *
     * Function to verify the integrity or existence of the employee ID provided
     *
     * */
    public EmployeeEntity verifyEmployeeId(Long idEmployee) throws RepositoryAccessException {
        if(idEmployee == null || idEmployee <= 0 || !(idEmployee instanceof Long)) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_INCORRECT_ID_EMPLOYEE);
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
        if(idProject == null || idProject <= 0 || !(idProject instanceof Long)) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_INCORRECT_ID_PROJECT);
        }

        Optional<ProjectEntity> projectEntity = projectRepository.findById(idProject);

        if(!projectEntity.isPresent()) {
            throw new RepositoryAccessException(READ_ACCESS_EXCEPTION_PROJECT_ID_NOT_FOUND);
        }

        return projectEntity.get();
    }

    /**
     *
     * Function to verify the integrity of the page and size provided
     *
     * */
    public void verifyPageAndSize(Integer page, Integer size) throws RepositoryAccessException {
        if(page == null || page < 0 || !(page instanceof Integer) ) {
            throw new PaginationException(PAGE_INCORRECT);
        }

        if(size == null || size <= 0 || !(size instanceof Integer)) {
            throw new PaginationException(SIZE_INCORRECT);
        }

    }

}
