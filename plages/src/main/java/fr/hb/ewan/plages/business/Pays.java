package fr.hb.ewan.plages.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pays {
	@Id
	private String code;
	
	private String nom;
	
	//Relation
    @OneToMany(mappedBy="pays")
    private List<Client> clients;
}
