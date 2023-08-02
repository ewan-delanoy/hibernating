package fr.hb.ewan.calendrier_gif.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fr.hb.ewan.calendrier_gif.business.Utilisateur;
import fr.hb.ewan.calendrier_gif.dto.UtilisateurDto;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

	UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

	UtilisateurDto toDto(Utilisateur utilisateur);

	Utilisateur toEntity(UtilisateurDto utilisateurDto);
}
