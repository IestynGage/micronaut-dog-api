package com.example.repository;

import com.example.entity.DogEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.PageableRepository;
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

@Repository
public interface DogRepository extends PageableRepository<DogEntity, Long> {

  Page<DogEntity> findAllByDeleted(Boolean deleted, Pageable pageable);


  Page<DogEntity> findAll(PredicateSpecification<DogEntity> spec, Pageable pageable);
}