package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICondicionCompraDAO;
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
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CondicionCompra agregar(CondicionCompra elemento) throws Exception {
        elemento = formatearStrings(elemento);
        String cuotas = String.valueOf(elemento.getCuotas());
        if (cuotas.length()>3) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en CUOTAS");
        }
        String dias = String.valueOf(elemento.getDias());
        if (dias.length()>2) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en DIAS");
        }
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CondicionCompra elemento) throws Exception {
        elemento = formatearStrings(elemento);
        //Obtiene longitud de cuotas, si supera 3 retorna error
        String cuotas = String.valueOf(elemento.getCuotas());
        if (cuotas.length()>3) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en CUOTAS");
        }
        //Obtiene longitud de dias, si supera 2 retorna error
        String dias = String.valueOf(elemento.getDias());
        if (dias.length()>2) {
            throw new DataIntegrityViolationException("Cantidad caracteres excedida en DIAS");
        }
        elementoDAO.save(elemento);
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
