package fr.hb.ewan.calendrier_gif.business;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class GifTeleverse extends Gif {

	private String nomFichierOriginal;
}
