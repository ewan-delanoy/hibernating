package fr.hb.ewan.plages.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nom;
    @NotNull(message = "L'utilisateur doit avoir un prénom.")
    protected String prenom;
    @Email(message = "L'utilisateur doit avoir un email valide." ) 
    protected String email;
    @NotNull(message = "L'utilisateur doit avoir un mot de passe.")
    @Size(min = 6, message = "Le mot de passe de l'utilisateur doit etre d'au moins {min} caracteres")
    protected String motDePasse;
}