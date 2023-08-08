package fr.hb.ewan.plages.business;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends Utilisateur{

	@Column(updatable=false)
    private LocalDateTime dateHeureInscription;
    
    //Relation
    @ManyToOne
    private Pays pays;
    
    @ManyToOne
    private LienDeParente lienDeParente;
    
    public Client() {
        super();
        this.dateHeureInscription = LocalDateTime.now();
    }
}