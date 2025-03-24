package com.openclassrooms.api.mappers;

import java.util.List;
import java.util.stream.Collectors; 

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import com.openclassrooms.api.dto.SubjectDTO;
import com.openclassrooms.api.dto.UserDTO;
import com.openclassrooms.api.models.Subject;
import com.openclassrooms.api.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "subscribedSubjects", expression = "java(mapSubjects(user.getSubscribedSubjects()))")
    UserDTO toDTO(User user);
    
    default List<SubjectDTO> mapSubjects(List<Subject> subjects) {
        if (subjects == null) return null;
        return subjects.stream()
                .map(SubjectDTO::fromEntity)
                .collect(Collectors.toList());
    }
}


