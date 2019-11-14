//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPagoDAO;
import ar.com.draimo.jitws.dao.IPagoItemDAO;
import ar.com.draimo.jitws.dao.ICompraComprobanteDAO;
import ar.com.draimo.jitws.model.PagoItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de PagoItem
 *
 * @author blas
 */
@Service
public class PagoItemService {

    //Define el dao
    @Autowired
    IPagoItemDAO elementoDAO;

    //Define el dao de pago
    @Autowired
    IPagoDAO pagoDAO;

    //Define el dao de compraComprobante
    @Autowired
    ICompraComprobanteDAO compraComprobanteDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PagoItem elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<PagoItem> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por cobranza
    public List<PagoItem> listarPorPago(int idPago) {
        return elementoDAO.findByPago(pagoDAO.findById(idPago).get());
    }

    //Obtiene una lista por CompraComprobante
    public List<PagoItem> listarPorCompraComprobante(int idCompraComprobante) {
        return elementoDAO.findByCompraComprobante(compraComprobanteDAO.findById(idCompraComprobante).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PagoItem agregar(PagoItem elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(PagoItem elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
