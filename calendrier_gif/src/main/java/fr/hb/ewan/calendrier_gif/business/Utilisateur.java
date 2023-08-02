package fr.hb.ewan.calendrier_gif.business;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Utilisateur {

	@ReadOnlyProperty
	private static final int NB_POINTS_INITIAL = 500;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(max = 50)
	private String nom;

	@NotBlank(message = "Merci de préciser votre prénom")
	private String prenom;

	@Email(message = "Merci de préciser une adresse email au bon format")
	@NotBlank(message = "Merci de préciser une adresse email")
	@Column(unique = true)
	@Pattern(regexp = "^([A-Za-z0-9-])+(.[A-Za-z0-9-]+)*@hb.com$", message = "Votre adresse doit se terminer par @hb.com")
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Size(min = 3, message = "Le mot de passe doit comporter au moins trois caractères")
	private String motDePasse;

	@ManyToOne
	@NotNull(message = "Merci de choisir un thème")
	private Theme theme;

	private int nbPoints;

	private LocalDateTime dateHeureInscription;

	// On aimerait récupérer tous les gifs d'un utilisateur dès que l'on récupère
	// l'utilisateur
	@OneToMany(mappedBy = "utilisateur")
	private List<Gif> gifs;

	// La cascade efface toutes les réactions postées par l'utilisateur que l'on
	// efface
	// Abcdefgh
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.REMOVE)
	private List<Reaction> reactions;

	private LocalDate dateDeNaissance;

	public Utilisateur() {
		nbPoints = NB_POINTS_INITIAL;
		dateHeureInscription = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", motDePasse="
				+ motDePasse + ", theme=" + theme + ", nbPoints=" + nbPoints + "]";
	}

}