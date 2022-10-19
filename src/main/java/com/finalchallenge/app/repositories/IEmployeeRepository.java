package com.finalchallenge.app.repositories;

import com.finalchallenge.app.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
