package com.example.repository;

import com.example.entity.DogEntity;
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;

public class DogSpecification {

  public static PredicateSpecification<DogEntity> nameLike(String dogName) {
    return (root, criteriaBuilder) ->
        dogName == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + dogName.toLowerCase() + "%");
  }

  public static PredicateSpecification<DogEntity> breedLike(String breed) {
    return (root, criteriaBuilder) ->
        breed == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("breed")), "%" + breed.toLowerCase() + "%");
  }

  public static PredicateSpecification<DogEntity> supplierLike(String supplier) {
    return (root, criteriaBuilder) ->
        supplier == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("supplier")), "%" + supplier.toLowerCase() + "%");
  }

  public static PredicateSpecification<DogEntity> isNotDeleted() {
    return (root, criteriaBuilder) -> criteriaBuilder.isFalse(root.get("deleted"));
  }

}
