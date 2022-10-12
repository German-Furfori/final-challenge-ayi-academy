package com.finalchallenge.app.repositories;

import com.finalchallenge.app.entities.DetailsEntity;
import com.finalchallenge.app.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    @Query("Select EE from EmployeeEntity EE where EE.dni = :dni")
    Optional<EmployeeEntity> findByDni(@Param("dni") String dni);

}
