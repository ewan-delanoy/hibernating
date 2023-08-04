/**
 * 
 */
package fr.hb.ewan.plages.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.hb.ewan.plages.business.LienDeParente;

@Repository
public interface LienDeParenteDao extends JpaRepository<LienDeParente, Long> {

}
