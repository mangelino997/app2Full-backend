//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ISeguimientoEstadoDAO;
import ar.com.draimo.jitws.model.SeguimientoEstado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio SeguimientoEstado
 * @author blas
 */

@Service
public class SeguimientoEstadoService {
    
    //Define la referencia al dao
    @Autowired
    ISeguimientoEstadoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SeguimientoEstado elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento!=null?elemento.getId()+1:1);
    }
    
    //Obtiene la lista completa
    public List<SeguimientoEstado> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene la lista de estados para reparto
    public List<SeguimientoEstado> listarParaReparto() {
        return elementoDAO.findByRepartoEntranteTrueAndRepartoSalienteTrue();
    }
    
    //Obtiene la lista de estados para reparto entrante
    public List<SeguimientoEstado> listarParaRepartoEntrante() {
        return elementoDAO.findByRepartoEntranteTrue();
    }
    
    //Obtiene una lista por nombre
    public List<SeguimientoEstado> listarPorNombre(String nombre) {
        return nombre.equals("*")?elementoDAO.findAll():
            elementoDAO.findByNombreContaining(nombre);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SeguimientoEstado agregar(SeguimientoEstado elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SeguimientoEstado elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private SeguimientoEstado formatearStrings(SeguimientoEstado elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}