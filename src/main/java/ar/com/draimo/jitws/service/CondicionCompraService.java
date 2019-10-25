//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICondicionCompraDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.CondicionCompra;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Condicion Compra
 * @author blas
 */

@Service
public class CondicionCompraService {
    
    //Define la referencia al dao
    @Autowired
    ICondicionCompraDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CondicionCompra elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene una lista completa
    public List<CondicionCompra> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<CondicionCompra> listarPorNombre(String nombre) {
        return nombre.equals("***") ? elementoDAO.findAll(): 
            elementoDAO.findByNombreContaining(nombre);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CondicionCompra agregar(CondicionCompra elemento) throws Exception {
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CondicionCompra elemento) throws Exception {
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        elementoDAO.save(elemento);
    }
    
    //Realiza el control de las cuotas y los dias
    private void controlarLongitud(CondicionCompra elemento) {
        String cuotas = String.valueOf(elemento.getCuotas());
        if (cuotas.length()>3) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " CUOTAS");
        }
        String dias = String.valueOf(elemento.getDias());
        if (dias.length()>2) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD + " DIAS");
        }
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private CondicionCompra formatearStrings(CondicionCompra elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}