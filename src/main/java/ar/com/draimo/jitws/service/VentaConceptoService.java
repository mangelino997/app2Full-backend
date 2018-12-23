package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IVentaConceptoDAO;
import ar.com.draimo.jitws.model.VentaConcepto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Provincia
 * @author blas
 */

@Service
public class VentaConceptoService {

    //Define la referencia al dao
    @Autowired
    IVentaConceptoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaConcepto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<VentaConcepto> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<VentaConcepto> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaConcepto agregar(VentaConcepto elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaConcepto elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(VentaConcepto elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private VentaConcepto formatearStrings(VentaConcepto elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
