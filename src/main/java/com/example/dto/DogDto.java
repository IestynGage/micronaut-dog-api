package com.example.dto;

import com.example.entity.ActiveStatus;
import com.example.entity.Gender;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDate;

@Serdeable
public class DogDto {

  private Long id;
  private String name;
  private String breed;
  private String supplier;
  private LocalDate birthDate;
  private Gender gender;
  private LocalDate dateAcquired;
  private ActiveStatus currentStatus;
  private LocalDate leavingDate;

  public DogDto() {
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

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public DogDto id(final Long id) {
    this.setId(id);
    return this;
  }

  public DogDto name(final String name) {
    this.setName(name);
    return this;
  }

  public DogDto breed(final String breed) {
    this.setBreed(breed);
    return this;
  }

  public DogDto supplier(final String supplier) {
    this.setSupplier(supplier);
    return this;
  }

  public DogDto birthDate(final LocalDate birthDate) {
    this.setBirthDate(birthDate);
    return this;
  }

  public DogDto gender(final Gender gender) {
    this.setGender(gender);
    return this;
  }

//  public DogDto dateAcquired(final LocalDate dateAcquired) {
//    this.set(dateAcquired);
//    return this;
//  }
//
//  public DogDto currentStatus(final ActiveStatus currentStatus) {
//    this.setCurrentStatus(currentStatus);
//    return this;
//  }
//
//  public DogDto leavingDate(final LocalDate leavingDate) {
//    this.setLeavingDate(leavingDate);
//    return this;
//  }



}
