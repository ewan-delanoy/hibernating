package fr.hb.ewan.plages.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
// @ToString
@NoArgsConstructor
public class Parasol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Merci de renseigner un numéro d'emplacement")
	@Range(min=1, max=36, message="Merci de donner un numéro d'emplacement entre {min} et {max}")
	private Byte numEmplacement;
	
	@NotNull(message = "Merci de choisir une file")
	@ManyToOne
	private File file;
	
}
