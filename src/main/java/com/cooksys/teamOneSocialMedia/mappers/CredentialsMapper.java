package com.cooksys.teamOneSocialMedia.mappers;

import com.cooksys.teamOneSocialMedia.dtos.CredentialsDto;
import com.cooksys.teamOneSocialMedia.entities.embeddables.Credentials;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {
    Credentials DtoToEntity (CredentialsDto credentialsDto);

}
