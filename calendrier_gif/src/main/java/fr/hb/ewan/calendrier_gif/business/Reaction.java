package fr.hb.ewan.calendrier_gif.business;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Reaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Gif gif;

	@ManyToOne
	private Utilisateur utilisateur;

	@ManyToOne
	private Emotion emotion;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime dateHeure;

	public Reaction() {
		dateHeure = LocalDateTime.now();
	}

	public Reaction(Utilisateur utilisateur, Emotion emotion, Gif gif) {
		super();
		this.gif = gif;
		this.utilisateur = utilisateur;
		this.emotion = emotion;
	}

}
