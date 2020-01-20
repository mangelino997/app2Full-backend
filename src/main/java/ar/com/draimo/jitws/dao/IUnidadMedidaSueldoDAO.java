/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.draimo.jitws.dao;
import ar.com.draimo.jitws.model.UnidadMedidaSueldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marina
 */
@Repository
public interface IUnidadMedidaSueldoDAO extends JpaRepository<UnidadMedidaSueldo, Integer>{

    public UnidadMedidaSueldo findTopByOrderByIdDesc();

    
}
