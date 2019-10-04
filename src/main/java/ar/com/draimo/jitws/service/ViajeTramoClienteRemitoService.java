//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.dao.IViajeTramoClienteDAO;
import ar.com.draimo.jitws.dao.IViajeTramoClienteRemitoDAO;
import ar.com.draimo.jitws.model.ViajeTramoClienteRemito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de ViajeTramoClienteRemito
 *
 * @author blas
 */
@Service
public class ViajeTramoClienteRemitoService {

    //Define el dao
    @Autowired
    IViajeTramoClienteRemitoDAO elementoDAO;

    //Define el dao deviajeTramo cliente
    @Autowired
    IViajeTramoClienteDAO viajeTramoClienteDAO;

    //Define el dao de tipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeTramoClienteRemito elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<ViajeTramoClienteRemito> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por ViajeTramoCliente
    public List<ViajeTramoClienteRemito> listarPorViajeTramoCliente(int idViajeTramoCliente) {
        return elementoDAO.findByViajeTramoCliente(viajeTramoClienteDAO.findById(
                idViajeTramoCliente).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeTramoClienteRemito agregar(ViajeTramoClienteRemito elemento) {
        elemento.setLetra("R");
        elemento.setTipoComprobante(tipoComprobanteDAO.findById(5).get());
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeTramoClienteRemito elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
}