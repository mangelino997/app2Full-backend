package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IProveedorDAO;
import ar.com.draimo.jitws.dao.IVehiculoProveedorDAO;
import ar.com.draimo.jitws.model.VehiculoProveedor;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio VehiculoProveedor
 * @author blas
 */

@Service
public class VehiculoProveedorService {

    //Define la referencia al dao
    @Autowired
    IVehiculoProveedorDAO elementoDAO;
    
    //Define la referencia al dao
    @Autowired
    IProveedorDAO proveedorDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VehiculoProveedor elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<VehiculoProveedor> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por proveedor
    public List<VehiculoProveedor> listarPorProveedor(int idProveedor) {
        return elementoDAO.findByProveedor(proveedorDAO.findById(idProveedor).get());
    }
    
    //Obtiene una lista por nombre
    public List<VehiculoProveedor> listarPorAlias(String alias) {
        if(alias.equals("***")) {
            return elementoDAO.findAll();
        }else {
            return elementoDAO.findByAliasContaining(alias);
        }
    }
    
    //Obtiene una lista por alias filtro remolque
    public List<VehiculoProveedor> listarPorAliasFiltroRemolque(String alias) {
        return elementoDAO.findByAliasContainingAndTipoVehiculo_EsRemolqueTrue(alias);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VehiculoProveedor agregar(VehiculoProveedor elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaAlta(new Date(new java.util.Date().getTime()));
        elementoDAO.saveAndFlush(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(VehiculoProveedor elemento) {
        elemento.setAlias(elemento.getDominio());
        elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VehiculoProveedor elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaUltimaMod(new Date(new java.util.Date().getTime()));
        elemento.setAlias(elemento.getDominio());
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private VehiculoProveedor formatearStrings(VehiculoProveedor elemento) {
        elemento.setDominio(elemento.getDominio().trim());
        if(elemento.getNumeroMotor() != null) {
            elemento.setNumeroMotor(elemento.getNumeroMotor().trim());
        }
        if(elemento.getNumeroChasis() != null) {
            elemento.setNumeroChasis(elemento.getNumeroChasis().trim());
        }
        elemento.setNumeroPoliza(elemento.getNumeroPoliza().trim());
        elemento.setNumeroRuta(elemento.getNumeroRuta().trim());
        return elemento;
    }

}
