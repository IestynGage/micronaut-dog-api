package com.example.mapper;
import com.example.dto.DogDto;
import com.example.entity.DogEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
    componentModel = "jsr330"
)
public interface DogMapper {

  DogMapper INSTANCE = Mappers.getMapper(DogMapper.class);

  DogDto toDto(DogEntity user);

  @Mapping(target = "deleted", expression = "java(false)")
  DogEntity toEntity(DogDto user);
}
