package fr.hb.ewan.calendrier_gif.business;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Emotion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	@NonNull
	@NotBlank(message = "merci de donner un nom à l\' emotion")
	private String nom;

	@NonNull
	private String code;

	@ToString.Exclude
	@OneToMany(mappedBy = "emotion", cascade = CascadeType.REMOVE)
	private List<Reaction> reactions;

	public Emotion(String nom, String code) {
		this.nom = nom;
		this.code = code;
	}

}
