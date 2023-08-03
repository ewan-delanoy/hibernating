package fr.hb.ewan.plages.business;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pays {
	@Id
	private String code;
	
	private String nom;
}
