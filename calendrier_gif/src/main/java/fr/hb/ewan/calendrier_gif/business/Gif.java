package fr.hb.ewan.calendrier_gif.business;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@FieldDefaults(level = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
public abstract class Gif {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	LocalDateTime dateHeureAjout;

	@Column(length = 255)
	String legende;

	@OneToMany(mappedBy = "gif", fetch = FetchType.EAGER)
	List<Reaction> reactions;

	@OneToOne
	Jour jour;

	@ManyToOne
	Utilisateur utilisateur;

	public Gif() {
		dateHeureAjout = LocalDateTime.now();
	}

}
