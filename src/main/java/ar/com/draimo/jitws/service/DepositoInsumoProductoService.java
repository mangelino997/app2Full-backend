package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IDepositoInsumoProductoDAO;
import ar.com.draimo.jitws.model.DepositoInsumoProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */

@Service
public class DepositoInsumoProductoService {

    @Autowired
    IDepositoInsumoProductoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        DepositoInsumoProducto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<DepositoInsumoProducto> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<DepositoInsumoProducto> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public DepositoInsumoProducto agregar(DepositoInsumoProducto elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(DepositoInsumoProducto elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(DepositoInsumoProducto elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private DepositoInsumoProducto formatearStrings(DepositoInsumoProducto elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
