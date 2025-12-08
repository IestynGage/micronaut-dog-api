package com.example.controller;

import com.example.dto.DogDto;
import com.example.entity.Gender;
import com.example.repository.DogRepository;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class DogControllerTest {

  @Inject
  @Client("/")
  HttpClient client;

  @Inject
  DogRepository dogRepository;

  @Test
  void testCreateDogReturnsDog() {
    var request = HttpRequest.POST("/api/dogs/dogs", createDog());
    HttpResponse<DogDto> response = client.toBlocking().exchange(request);

    assertEquals(201, response.getStatus().getCode());
    var responseBody = response.getBody(DogDto.class);
    assertTrue(responseBody.isPresent());
    var responseDog = responseBody.get();
    assertEquals("Max", responseDog.getName());
    assertEquals("Labrador", responseDog.getBreed());
    assertEquals("HappyDogs Inc.", responseDog.getSupplier());
    assertEquals(Gender.MALE, responseDog.getGender());
  }

  @Test
  void testCreateDogSavesDog() {
    var request = HttpRequest.POST("/api/dogs/dogs", createDog());
    HttpResponse<DogDto> response = client.toBlocking().exchange(request);
    assertEquals(201, response.getStatus().getCode());

    var savedDogOptional = dogRepository.findById(1L);
    assertTrue(savedDogOptional.isPresent());

    var savedDog = savedDogOptional.get();
    assertEquals("Max", savedDog.getName());
    assertEquals("Labrador", savedDog.getBreed());
    assertEquals("HappyDogs Inc.", savedDog.getSupplier());
    assertEquals(Gender.MALE, savedDog.getGender());

  }

  private DogDto createDog() {
    return new DogDto()
        .name("Max")
        .breed("Labrador")
        .supplier("HappyDogs Inc.")
        .gender(Gender.MALE);
//        .currentStatus(ActiveStatus.ACTIVE);
  }

}
