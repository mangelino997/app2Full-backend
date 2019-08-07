package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITipoComprobanteDAO;
import ar.com.draimo.jitws.model.VentaItemConcepto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IVentaItemConceptoDAO;
import ar.com.draimo.jitws.model.TipoComprobante;

/**
 * Servicio Venta Item Concepto
 * @author blas
 */

@Service
public class VentaItemConceptoService {

    //Define la referencia al dao
    @Autowired
    IVentaItemConceptoDAO elementoDAO;
    
    //Define la referencia al dao TipoComprobante
    @Autowired
    ITipoComprobanteDAO tipoComprobanteDAO;
    
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
    
    //Obtiene la lista por tipo de comprobante
    public List<VentaItemConcepto> listarPorTipoComprobante(int idTipoComprobante) {
        //Obtiene el tipo de comprobante
        TipoComprobante tipoComprobante = tipoComprobanteDAO.findById(idTipoComprobante).get();
        return elementoDAO.findByTipoComprobanteAndEstaHabilitadoTrue(tipoComprobante);
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
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private VentaItemConcepto formatearStrings(VentaItemConcepto elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
