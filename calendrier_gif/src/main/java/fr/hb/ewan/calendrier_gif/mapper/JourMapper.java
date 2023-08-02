package fr.hb.ewan.calendrier_gif.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fr.hb.ewan.calendrier_gif.business.Jour;
import fr.hb.ewan.calendrier_gif.dto.JourDto;

@Mapper(componentModel = "spring")
public interface JourMapper {

	JourMapper INSTANCE = Mappers.getMapper(JourMapper.class);

	JourDto toDto(Jour jour);
	Jour toEntity(JourDto jourDto);
}
