package com.openclassrooms.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.api.dto.PostDTO;
import com.openclassrooms.api.dto.SubjectDTO;
import com.openclassrooms.api.dto.UserDTO;
import com.openclassrooms.api.models.Post;
import com.openclassrooms.api.models.Subject;
import com.openclassrooms.api.models.User;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(source = "subject", target = "subject") // Correspond au champ "subject" dans PostDTO
    @Mapping(source = "author", target = "author")   // Correspond au champ "author" dans PostDTO
    PostDTO toDTO(Post post);

    SubjectDTO toSubjectDTO(Subject subject);

    UserDTO toUserDTO(User user);
}


