package fr.hb.ewan.calendrier_gif.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UtilisateurDto {

	@Size(max = 50)
	String nom;

	@NotBlank(message = "Merci de préciser votre prénom")
	String prenom;

	@Email(message = "Merci de préciser une adresse email au bon format")
	@NotBlank(message = "Merci de préciser une adresse email")
	@Pattern(regexp = "^([A-Za-z0-9-])+(.[A-Za-z0-9-]+)*hb.com$", message = "Votre adresse doit se terminer par hb.com")
	String email;

	@Size(min = 3, message = "Le mot de passe doit comporter au moins trois caractères")
	String motDePasse;

	@NotNull(message = "Merci de choisir un thème")
	ThemeDto themeDto;

	int nbPoints;

	LocalDate dateDeNaissance;
}
