package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.IProveedorDAO;
import ar.com.wecoode.jitws.model.Proveedor;
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
        elementoDAO.saveAndFlush(elemento);
        elemento.setAlias(elemento.getId() + " - " + elemento.getRazonSocial() 
                + " - " + elemento.getNombreFantasia() + " - " + elemento.getNumeroDocumento());
        return elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Proveedor elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Proveedor elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Proveedor formatearStrings(Proveedor elemento) {
        elemento.setRazonSocial(Funcion.convertirATitulo(elemento.getRazonSocial().trim()));
        elemento.setNombreFantasia(Funcion.convertirATitulo(elemento.getNombreFantasia().trim()));
        elemento.setDomicilio(Funcion.primerLetraAMayuscula(elemento.getDomicilio().trim()));
        elemento.setNumeroDocumento(elemento.getNumeroDocumento().trim());
        elemento.setNumeroIIBB(elemento.getNumeroIIBB().trim());
        elemento.setSitioWeb(elemento.getSitioWeb().trim().toLowerCase());
        elemento.setTelefono(elemento.getTelefono().trim());
        elemento.setObservaciones(Funcion.primerLetraAMayuscula(elemento.getObservaciones().trim()));
        elemento.setNotaIngresarComprobante(Funcion.primerLetraAMayuscula(elemento.getNotaIngresarComprobante().trim()));
        elemento.setNotaImpresionOrdenPago(Funcion.primerLetraAMayuscula(elemento.getNotaImpresionOrdenPago().trim()));
        elemento.setNumeroCuenta(elemento.getNumeroCuenta().trim());
        elemento.setTitular(Funcion.convertirATitulo(elemento.getTitular().trim()));
        elemento.setNumeroCBU(elemento.getNumeroCBU().trim());
        elemento.setAliasCBU(elemento.getAliasCBU().trim());
        return elemento;
    }

}
