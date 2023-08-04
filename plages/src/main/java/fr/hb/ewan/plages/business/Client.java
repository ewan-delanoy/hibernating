package fr.hb.ewan.plages.business;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Client extends Utilisateur{

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