package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.VentaItemConcepto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IVentaItemConceptoDAO;

/**
 * Servicio Venta Item Concepto
 * @author blas
 */

@Service
public class VentaItemConceptoService {

    //Define la referencia al dao
    @Autowired
    IVentaItemConceptoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VentaItemConcepto elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<VentaItemConcepto> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<VentaItemConcepto> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VentaItemConcepto agregar(VentaItemConcepto elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VentaItemConcepto elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(VentaItemConcepto elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private VentaItemConcepto formatearStrings(VentaItemConcepto elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}