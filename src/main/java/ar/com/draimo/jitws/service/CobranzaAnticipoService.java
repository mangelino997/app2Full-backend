//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICobranzaDAO;
import ar.com.draimo.jitws.dao.ICobranzaAnticipoDAO;
import ar.com.draimo.jitws.model.CobranzaAnticipo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de CobranzaAnticipo
 *
 * @author blas
 */
@Service
public class CobranzaAnticipoService {

    //Define el dao
    @Autowired
    ICobranzaAnticipoDAO elementoDAO;

    //Define el dao de cobranza
    @Autowired
    ICobranzaDAO cobranzaDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CobranzaAnticipo elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<CobranzaAnticipo> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por cobranza
    public List<CobranzaAnticipo> listarPorCobranza(int idCobranza) {
        return elementoDAO.findByCobranza(cobranzaDAO.findById(idCobranza).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CobranzaAnticipo agregar(CobranzaAnticipo elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CobranzaAnticipo elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
