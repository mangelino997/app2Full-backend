//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICobranzaDAO;
import ar.com.draimo.jitws.dao.ICobranzaItemDAO;
import ar.com.draimo.jitws.dao.IVentaComprobanteDAO;
import ar.com.draimo.jitws.model.CobranzaItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de CobranzaItem
 *
 * @author blas
 */
@Service
public class CobranzaItemService {

    //Define el dao
    @Autowired
    ICobranzaItemDAO elementoDAO;

    //Define el dao de cobranza
    @Autowired
    ICobranzaDAO cobranzaDAO;

    //Define el dao de ventaComprobante
    @Autowired
    IVentaComprobanteDAO ventaComprobanteDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CobranzaItem elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<CobranzaItem> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por cobranza
    public List<CobranzaItem> listarPorCobranza(int idCobranza) {
        return elementoDAO.findByCobranza(cobranzaDAO.findById(idCobranza).get());
    }

    //Obtiene una lista por VentaComprobante
    public List<CobranzaItem> listarPorVentaComprobante(int idVentaComprobante) {
        return elementoDAO.findByVentaComprobante(ventaComprobanteDAO.findById(idVentaComprobante).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CobranzaItem agregar(CobranzaItem elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CobranzaItem elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
