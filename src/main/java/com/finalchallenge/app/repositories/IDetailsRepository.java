package com.finalchallenge.app.repositories;

import com.finalchallenge.app.entities.DetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetailsRepository extends JpaRepository<DetailsEntity, Long> {
}
