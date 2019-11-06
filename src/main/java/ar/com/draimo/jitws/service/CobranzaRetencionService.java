//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICobranzaDAO;
import ar.com.draimo.jitws.dao.ICobranzaRetencionDAO;
import ar.com.draimo.jitws.dao.ITipoRetencionDAO;
import ar.com.draimo.jitws.model.CobranzaRetencion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de CobranzaRetencion
 *
 * @author blas
 */
@Service
public class CobranzaRetencionService {

    //Define el dao
    @Autowired
    ICobranzaRetencionDAO elementoDAO;

    //Define el dao de cobranza
    @Autowired
    ICobranzaDAO cobranzaDAO;

    //Define el dao de TipoRetencion
    @Autowired
    ITipoRetencionDAO tipoRetencionDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CobranzaRetencion elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<CobranzaRetencion> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por cobranza
    public List<CobranzaRetencion> listarPorCobranza(int idCobranza) {
        return elementoDAO.findByCobranza(cobranzaDAO.findById(idCobranza).get());
    }

    //Obtiene una lista por TipoRetencion
    public List<CobranzaRetencion> listarPorTipoRetencion(int idTipoRetencion) {
        return elementoDAO.findByTipoRetencion(tipoRetencionDAO.findById(idTipoRetencion).get());
    }

    //Obtiene una lista por puntoVenta letra y numero
    public List<CobranzaRetencion> listarPorPuntoVentaLetraYNumero(int puntoVenta,
            String letra, int numero) {
        return elementoDAO.findByPuntoVentaAndLetraAndNumero(puntoVenta, letra, numero);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CobranzaRetencion agregar(CobranzaRetencion elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CobranzaRetencion elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
