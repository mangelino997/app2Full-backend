//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPagoDAO;
import ar.com.draimo.jitws.dao.IPagoAnticipoDAO;
import ar.com.draimo.jitws.model.PagoAnticipo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de PagoAnticipo
 *
 * @author blas
 */
@Service
public class PagoAnticipoService {

    //Define el dao
    @Autowired
    IPagoAnticipoDAO elementoDAO;

    //Define el dao de Pago
    @Autowired
    IPagoDAO pagoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PagoAnticipo elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<PagoAnticipo> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por Pago
    public List<PagoAnticipo> listarPorPago(int idPago) {
        return elementoDAO.findByPago(pagoDAO.findById(idPago).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PagoAnticipo agregar(PagoAnticipo elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(PagoAnticipo elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
