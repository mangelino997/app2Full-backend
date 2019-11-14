//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPagoDAO;
import ar.com.draimo.jitws.dao.IPagoRetencionDAO;
import ar.com.draimo.jitws.dao.ITipoRetencionDAO;
import ar.com.draimo.jitws.model.PagoRetencion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de PagoRetencion
 *
 * @author blas
 */
@Service
public class PagoRetencionService {

    //Define el dao
    @Autowired
    IPagoRetencionDAO elementoDAO;

    //Define el dao de pago
    @Autowired
    IPagoDAO pagoDAO;

    //Define el dao de TipoRetencion
    @Autowired
    ITipoRetencionDAO tipoRetencionDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PagoRetencion elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<PagoRetencion> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por pago
    public List<PagoRetencion> listarPorPago(int pago) {
        return elementoDAO.findByPago(pagoDAO.findById(pago).get());
    }

    //Obtiene una lista por TipoRetencion
    public List<PagoRetencion> listarPorTipoRetencion(int idTipoRetencion) {
        return elementoDAO.findByTipoRetencion(tipoRetencionDAO.findById(idTipoRetencion).get());
    }

    //Obtiene una lista por puntoVenta letra y numero
    public List<PagoRetencion> listarPorPuntoVentaLetraYNumero(int puntoVenta,
            String letra, int numero) {
        return elementoDAO.findByPuntoVentaAndLetraAndNumero(puntoVenta, letra, numero);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PagoRetencion agregar(PagoRetencion elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(PagoRetencion elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
