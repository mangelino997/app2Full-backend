//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICobradorDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Cobrador;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Cobrador
 *
 * @author blas
 */
@Service
public class CobradorService {

    //Define la referencia al dao
    @Autowired
    ICobradorDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Cobrador elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<Cobrador> listar() {
        return elementoDAO.findAllByOrderByNombreAsc();
    }

    //Obtiene una lista por nombre
    public List<Cobrador> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll():
            elementoDAO.findByNombreContaining(nombre);
    }

    //Obtiene una lista por esta activo y ordenado por nombre
    public List<Cobrador> listarPorEstaActivoTrue() {
        return elementoDAO.findByEstaActivoTrueOrderByNombreAsc();
    }

    //Obtiene la moneda principal por defecto para cliente eventual
    public Cobrador obtenerPorDefecto() {
        return elementoDAO.findByPorDefectoClienteEventualTrue();
    }

    //Establece el cobrador por defecto
    @Transactional(rollbackFor = Exception.class)
    public void establecerCobradorPorDefecto(int idCobrador) {
        Cobrador cobrador = elementoDAO.findById(idCobrador).get();
        cobrador.setPorDefectoClienteEventual(false);
        elementoDAO.save(cobrador);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Cobrador agregar(Cobrador elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        Cobrador cobrador = elementoDAO.findByPorDefectoClienteEventualTrue();
        if (cobrador != null) {
            if (elemento.isPorDefectoClienteEventual()) {
                cobrador.setPorDefectoClienteEventual(false);
                elementoDAO.save(cobrador);
            }
        } else {
            elemento.setPorDefectoClienteEventual(true);
        }
        return elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public Cobrador actualizar(Cobrador elemento) {
        elemento = formatearStrings(elemento);
        Cobrador cobrador = elementoDAO.findByPorDefectoClienteEventualTrue();
        if (cobrador == null || cobrador.getId() == elemento.getId()) {
            elemento.setPorDefectoClienteEventual(true);
        } else {
            if (elemento.isPorDefectoClienteEventual()) {
                cobrador.setPorDefectoClienteEventual(false);
                elementoDAO.save(cobrador);
            }
        }
        return elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        Cobrador cobrador = elementoDAO.findByPorDefectoClienteEventualTrue();
        if (cobrador.getId() == elemento) {
            throw new DataIntegrityViolationException(MensajeRespuesta.ERROR_COBRADOR_POR_DEFECTO);
        } else {
            elementoDAO.deleteById(elemento);
        }
    }

    //Formatea los strings
    private Cobrador formatearStrings(Cobrador elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}