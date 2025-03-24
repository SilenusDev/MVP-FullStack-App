package com.openclassrooms.api.mappers;

import org.mapstruct.Mapper;

import com.openclassrooms.api.dto.SubjectDTO;
import com.openclassrooms.api.models.Subject;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectDTO toDto(Subject subject);
}