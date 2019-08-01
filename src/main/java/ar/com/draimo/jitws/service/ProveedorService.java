package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IProveedorDAO;
import ar.com.draimo.jitws.model.Proveedor;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Proveedor
 * @author blas
 */

@Service
public class ProveedorService {

    //Define la referencia al dao
    @Autowired
    IProveedorDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Proveedor elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Proveedor> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Proveedor> listarPorAlias(String alias) {
        if(alias.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByAliasContaining(alias);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Proveedor agregar(Proveedor elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(Proveedor elemento) {
        elemento.setAlias(elemento.getId() + " - " + elemento.getRazonSocial() 
                + " - " + elemento.getNombreFantasia() + " - " + elemento.getNumeroDocumento());
        elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Proveedor elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaUltimaMod(new Date(new java.util.Date().getTime()));
        elemento.setAlias(elemento.getId() + " - " + elemento.getRazonSocial() 
                + " - " + elemento.getNombreFantasia() + " - " + elemento.getNumeroDocumento());
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Proveedor elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Proveedor formatearStrings(Proveedor elemento) {
        elemento.setRazonSocial(elemento.getRazonSocial().trim().toUpperCase());
        elemento.setDomicilio(elemento.getDomicilio().trim());
        elemento.setNumeroDocumento(elemento.getNumeroDocumento().trim());
        if(elemento.getNombreFantasia()!= null) {
        elemento.setNombreFantasia(elemento.getNombreFantasia().trim());
        }
        if(elemento.getNumeroIIBB() != null) {
            elemento.setNumeroIIBB(elemento.getNumeroIIBB().trim());
        }
        if(elemento.getSitioWeb() != null) {
            elemento.setSitioWeb(elemento.getSitioWeb().trim().toLowerCase());
        }
        if(elemento.getTelefono() != null) {
            elemento.setTelefono(elemento.getTelefono().trim());
        }
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if(elemento.getNotaIngresarComprobante() != null) {
            elemento.setNotaIngresarComprobante(elemento.getNotaIngresarComprobante().trim());
        }
        if(elemento.getNotaImpresionOrdenPago() != null) {
            elemento.setNotaImpresionOrdenPago(elemento.getNotaImpresionOrdenPago().trim());
        }
        if(elemento.getNumeroCuenta() != null) {
            elemento.setNumeroCuenta(elemento.getNumeroCuenta().trim());
        }
        if(elemento.getTitular() != null) {
            elemento.setTitular(elemento.getTitular().trim());
        }
        if(elemento.getNumeroCBU() != null) {
            elemento.setNumeroCBU(elemento.getNumeroCBU().trim());
        }
        if(elemento.getAliasCBU() != null) {
            elemento.setAliasCBU(elemento.getAliasCBU().trim());
        }
        return elemento;
    }

}
