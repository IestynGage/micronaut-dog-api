package com.example.entity;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.time.LocalDate;

@Serdeable
@Introspected
@Entity
public class DogEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String breed;

  // This should be MANY-TO-ONE relationship with a supplier table
  private String supplier;

  // This should be ONE-TO-ONE relationship with a BADGE table
//  private String badgeId;

  private LocalDate birthDate;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private LocalDate dateAcquired;

  @Enumerated(EnumType.STRING)
  private ActiveStatus currentStatus;

  private LocalDate leavingDate;

  // This should be a ONE-TO-ONE relationship with an Events table.
  // This table should contain ID, timestamp and user ID.
  private boolean deleted;

  public DogEntity() {}

  public DogEntity(String name) {
    this.name = name;
    this.deleted = false;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getDeleted() {
    return deleted;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public String getBreed() {
    return breed;
  }

  public void setBreed(String breed) {
    this.breed = breed;
  }

  public String getSupplier() {
    return supplier;
  }

  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }
}
