package com.example.controller;

import com.example.dto.DogDto;
import com.example.entity.DogEntity;
import com.example.mapper.DogMapper;
import com.example.repository.DogRepository;
import com.example.repository.DogSpecification;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.model.Sort;
import io.micronaut.data.repository.jpa.criteria.PredicateSpecification;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.annotation.Nullable;

import static io.micronaut.http.MediaType.APPLICATION_JSON;

@Controller("/api/dogs/dogs")
public class DogController {

  DogRepository _repository;
  private final DogMapper _mapper;

  public DogController(DogRepository repository, DogMapper mapper) {
    _repository = repository;
    _mapper = mapper;
  }

  @Post
  public HttpResponse<DogDto> create(@Body DogDto dog) {
    var savedDog = _repository.save(_mapper.toEntity(dog));
    return HttpResponse.created(_mapper.toDto(savedDog));
  }

  @Put
  public HttpResponse<?> update(@Body DogDto dog) {
    var unsavedDog = _mapper.toEntity(dog);

    if (dog.getId() == null) {
      var savedDog = _repository.save(unsavedDog);
      return HttpResponse.created(_mapper.toDto(savedDog));
    }

    var existingDog = _repository.findById(dog.getId());

    if (existingDog.isPresent()) {
      if (existingDog.get().isDeleted()) {
        return HttpResponse.badRequest("Can not edit deleted dog");
      }
      var updatedDog = _repository.update(unsavedDog);
      return HttpResponse.ok(_mapper.toDto(updatedDog));
    }

    return HttpResponse.notFound();
  }

  @Get("/{id}")
  public HttpResponse<DogDto> get(Long id) {
    var dog = _repository.findById(id);

    if (dog.isEmpty() || dog.get().getDeleted()) {
      return HttpResponse.notFound();
    }

    return HttpResponse.ok(_mapper.toDto(dog.get()));
  }

  private static final Sort ID_SORT = Sort.of(Sort.Order.asc("id"));

  @Get(produces = APPLICATION_JSON, value = "/{?pageable}")
  public Iterable<DogDto> getAll(
      @Nullable Pageable pageable,
      @Nullable @QueryValue String dog,
      @Nullable @QueryValue String breed,
      @Nullable @QueryValue String supplier) {

    // TODO check documentation that there's no SQL injection attack
    PredicateSpecification<DogEntity> a = DogSpecification.isNotDeleted()
        .and(DogSpecification.nameLike(dog))
        .and(DogSpecification.breedLike(breed))
        .and(DogSpecification.supplierLike(supplier));

    final Page<DogEntity> page =_repository.findAll(a, pageable);
    return page.getContent().stream().map(_mapper::toDto).toList();
  }

  @Delete("/{id}")
  public HttpResponse<?> delete(Long id) {
    var optional = _repository.findById(id);
    if (optional.isPresent() && optional.get().getDeleted().equals(Boolean.FALSE)) {
      var dog = optional.get();
      dog.setDeleted(true);
      _repository.update(dog);
      return HttpResponse.noContent();
    }

    return HttpResponse.notFound();
  }


}
