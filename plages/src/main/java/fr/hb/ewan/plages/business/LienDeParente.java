package fr.hb.ewan.plages.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LienDeParente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String typeDeParente;
    
    private float coefficient;
    
    //Relation
    @OneToMany(mappedBy="lienDeParente")
    private List<Client> clients;
    
    public LienDeParente(String typeDeParente, float coefficient) {
        this();
        this.typeDeParente = typeDeParente;
        this.coefficient = coefficient;
    }
}