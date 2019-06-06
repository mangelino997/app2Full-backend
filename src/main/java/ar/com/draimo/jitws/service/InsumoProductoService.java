package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.InsumoProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IInsumoProductoDAO;
import java.math.BigDecimal;

/**
 * Servicio InsumoProducto
 * @author blas
 */

@Service
public class InsumoProductoService {
    
    //Define la referencia al dao
    @Autowired
    IInsumoProductoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        InsumoProducto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<InsumoProducto> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<InsumoProducto> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene una lista de combustibles
    public List<InsumoProducto> listarCombustibles() {
        return elementoDAO.findByRubroProducto_EsCombustibleTrue();
    }
    
    //Obtiene una lista de insumos
    public List<InsumoProducto> listarInsumos() {
        return elementoDAO.findByRubroProducto_EsInsumoTrue();
    }
    
    //Obtiene precio unitario por insumo
    public BigDecimal obtenerPrecioUnitario(int idInsumoProducto) {
        InsumoProducto insumoProducto = elementoDAO.findById(idInsumoProducto).get();
        return insumoProducto.getPrecioUnitarioViaje();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public InsumoProducto agregar(InsumoProducto elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(InsumoProducto elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(InsumoProducto elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private InsumoProducto formatearStrings(InsumoProducto elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}
