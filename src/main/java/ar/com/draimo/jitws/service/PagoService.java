//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPagoDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.model.Pago;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de Pago
 *
 * @author blas
 */
@Service
public class PagoService {

    //Define el dao
    @Autowired
    IPagoDAO elementoDAO;

    //Define el dao de empresa
    @Autowired
    IEmpresaDAO empresaDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Pago elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<Pago> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por empresa
    public List<Pago> listarPorEmpresa(int idEmpresa) {
        return elementoDAO.findByEmpresa(empresaDAO.findById(idEmpresa).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Pago agregar(Pago elemento) {
        elemento.setFechaRegistracion(new Timestamp(new java.util.Date().getTime()));
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Pago elemento) {
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
