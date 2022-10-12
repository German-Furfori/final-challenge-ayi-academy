package com.finalchallenge.app.repositories;

import com.finalchallenge.app.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepository extends JpaRepository<ProjectEntity, Long> {
}
