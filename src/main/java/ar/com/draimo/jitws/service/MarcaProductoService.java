package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IMarcaProductoDAO;
import ar.com.draimo.jitws.model.MarcaProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Marca Producto
 * @author blas
 */

@Service
public class MarcaProductoService {
    
    //Define la referencia al dao
    @Autowired
    IMarcaProductoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        MarcaProducto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<MarcaProducto> listar() {
        return elementoDAO.findAllByOrderByNombreAsc();
    }
    
    //Obtiene una lista por nombre
    public List<MarcaProducto> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public MarcaProducto agregar(MarcaProducto elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(MarcaProducto elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private MarcaProducto formatearStrings(MarcaProducto elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}
