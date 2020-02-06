/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.draimo.jitws.dao;

import ar.com.draimo.jitws.model.ConceptoSueldoLiqAsoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *Interfaz DAO ConceptoSueldoLiqAsoc
 * @author blas
 */
@Repository
public interface IConceptoSueldoLiqAsocDAO extends JpaRepository<ConceptoSueldoLiqAsoc, Integer> {
}
