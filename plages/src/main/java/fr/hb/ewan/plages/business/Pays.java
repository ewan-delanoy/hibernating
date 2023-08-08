package fr.hb.ewan.plages.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Pays {
	@Id
	@NonNull
	private String code;
	
	@NonNull
	private String nom;
	
	//Relation
	@ToString.Exclude
    @OneToMany(mappedBy="pays")
    private List<Client> clients;
}
