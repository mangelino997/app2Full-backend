package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICompraComprobanteDAO;
import ar.com.draimo.jitws.model.CompraComprobante;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */

@Service
public class CompraComprobanteService {

    @Autowired
    ICompraComprobanteDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CompraComprobante elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<CompraComprobante> listar() {
        return elementoDAO.findAll();
    }
    
    public boolean verificarUnicidadComprobante(int idProveedor, String codigoAfip, int puntoVenta, int numero) {
        if(!elementoDAO.verificarUnicidad(idProveedor, codigoAfip, puntoVenta, numero).isEmpty()) {
            return true;
        }else {
            return false;
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CompraComprobante agregar(CompraComprobante elemento) {
        Timestamp fechaRegistracion= new Timestamp(new java.util.Date().getTime());
        elemento.setFechaRegistracion(fechaRegistracion);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CompraComprobante elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
