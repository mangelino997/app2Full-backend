//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICobranzaDAO;
import ar.com.draimo.jitws.dao.ICobranzaReciboDAO;
import ar.com.draimo.jitws.dao.ITalonarioReciboDAO;
import ar.com.draimo.jitws.model.CobranzaRecibo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de CobranzaRecibo
 *
 * @author blas
 */
@Service
public class CobranzaReciboService {

    //Define el dao
    @Autowired
    ICobranzaReciboDAO elementoDAO;

    //Define el dao de cobranza
    @Autowired
    ICobranzaDAO cobranzaDAO;

    //Define el dao de TalonarioRecibo
    @Autowired
    ITalonarioReciboDAO talonarioReciboDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CobranzaRecibo elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<CobranzaRecibo> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por cobranza
    public List<CobranzaRecibo> listarPorCobranza(int idCobranza) {
        return elementoDAO.findByCobranza(cobranzaDAO.findById(idCobranza).get());
    }

    //Obtiene una lista por VentaComprobante
    public List<CobranzaRecibo> listarPorTalonarioRecibo(int idTalonario) {
        return elementoDAO.findByTalonarioRecibo(talonarioReciboDAO.findById(idTalonario).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CobranzaRecibo agregar(CobranzaRecibo elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CobranzaRecibo elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
