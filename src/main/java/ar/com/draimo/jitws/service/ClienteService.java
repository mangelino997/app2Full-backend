package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IClienteDAO;
import ar.com.draimo.jitws.dao.ICondicionVentaDAO;
import ar.com.draimo.jitws.model.Cliente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Cliente
 * @author blas
 */

@Service
public class ClienteService {
    
    //Define la referencia al dao
    @Autowired
    IClienteDAO elementoDAO;
    
    //Define la referencia al dao CondicionVenta
    @Autowired
    ICondicionVentaDAO condicionVentaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Cliente elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene una lista completa
    public List<Cliente> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene por id
    public Cliente obtenerPorId(int id) {
        return elementoDAO.findById(id).get();
    }
    
    //Obtiene una lista por alias
    public List<Cliente> listarPorAlias(String alias) {
        if(alias.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByAliasContaining(alias);
        }
    }
    
    //Agrega un cliente eventual
    @Transactional(rollbackFor = Exception.class)
    public Cliente agregarClienteEventual(Cliente elemento) {
        elemento = formatearString(elemento);
        elemento.setEsCuentaCorriente(false);
        elemento.setCondicionVenta(condicionVentaDAO.findById(1).get());
        elemento.setEsSeguroPropio(false);
        elemento.setImprimirControlDeuda(false);
        elemento.setAlias(elemento.getId() + " - " + elemento.getRazonSocial() 
                + " - " + elemento.getNombreFantasia() + " - " + elemento.getNumeroDocumento());
        return elementoDAO.saveAndFlush(elemento);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Cliente agregar(Cliente elemento) {
        elemento = formatearString(elemento);
        elementoDAO.saveAndFlush(elemento);
        elemento.setAlias(elemento.getId() + " - " + elemento.getRazonSocial() 
                + " - " + elemento.getNombreFantasia() + " - " + elemento.getNumeroDocumento());
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Cliente elemento) {
        elemento = formatearString(elemento);
        elemento.setAlias(elemento.getId() + " - " + elemento.getRazonSocial() 
                + " - " + elemento.getNombreFantasia() + " - " + elemento.getNumeroDocumento());
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Cliente elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los string
    private Cliente formatearString(Cliente elemento) {
        elemento.setRazonSocial(elemento.getRazonSocial().trim().toUpperCase());
        if(elemento.getNombreFantasia() != null) {
            elemento.setNombreFantasia(elemento.getNombreFantasia().trim());
        }
        elemento.setDomicilio(elemento.getDomicilio().trim());
        if(elemento.getTelefono() != null) {
            elemento.setTelefono(elemento.getTelefono().trim());
        }
        if(elemento.getSitioWeb() != null) {
            elemento.setSitioWeb(elemento.getSitioWeb().toLowerCase().trim());
        }
        elemento.setNumeroDocumento(elemento.getNumeroDocumento().trim());
        if(elemento.getNumeroIIBB() != null) {
            elemento.setNumeroIIBB(elemento.getNumeroIIBB().trim());
        }
        if(elemento.getNumeroPolizaSeguro() != null) {
            elemento.setNumeroPolizaSeguro(elemento.getNumeroPolizaSeguro().trim());
        }
        if(elemento.getObservaciones() != null) {
            elemento.setObservaciones(elemento.getObservaciones().trim());
        }
        if(elemento.getNotaEmisionComprobante() != null) {
            elemento.setNotaEmisionComprobante(elemento.getNotaEmisionComprobante().trim());
        }
        if(elemento.getNotaImpresionComprobante() != null) {
            elemento.setNotaImpresionComprobante(elemento.getNotaImpresionComprobante().trim());
        }
        if(elemento.getNotaImpresionRemito() != null) {
            elemento.setNotaImpresionRemito(elemento.getNotaImpresionRemito().trim());
        }
        return elemento;
    }
    
}
