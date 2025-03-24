package com.openclassrooms.api.mappers;

import java.util.List;
import java.util.stream.Collectors; 

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

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

// @Mapper(componentModel = "spring")
// public interface UserMapper {
//     UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

//     @Mapping(source = "created_at", target = "createdAt")
//     UserDTO toDto(User user);

//     @Mapping(source = "createdAt", target = "created_at")
//     User toEntity(UserDTO userDto);
// }
