package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.model.TipoComprobante;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio TipoComprobante
 * @author blas
 */

@Service
public class TipoComprobanteService {

    //Define la referencia al dao
    @Autowired
    ITipoComprobanteDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TipoComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene un registro por id
    public TipoComprobante obtenerPorId(int id) {
        return elementoDAO.findById(id).get();
    }
    
    //Obtiene la lista completa
    public List<TipoComprobante> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene un listado por nombre
    public List<TipoComprobante> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene una lista por esta activo ingreso carga igual true
    public List<TipoComprobante> listarPorEstaActivoIngresoCargaTrue() {
        return elementoDAO.findByEstaActivoIngresoCargaTrue();
    }
    
    //Obtiene una lista por esta activo Reparto igual true
    public List<TipoComprobante> listarPorEstaActivoRepartoTrue() {
        return elementoDAO.findByEstaActivoRepartoTrue();
    }
    
    //Obtiene una lista por numeracion punto venta igual true
    public List<TipoComprobante> listarPorNumeracionPuntoVentaTrue() {
        return elementoDAO.findByNumeracionPuntoVentaTrue();
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TipoComprobante agregar(TipoComprobante elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TipoComprobante elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(TipoComprobante elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private TipoComprobante formatearStrings(TipoComprobante elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        elemento.setAbreviatura(elemento.getAbreviatura().trim());
        return elemento;
    }

}
