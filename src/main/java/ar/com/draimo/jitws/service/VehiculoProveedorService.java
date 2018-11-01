package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IVehiculoProveedorDAO;
import ar.com.draimo.jitws.model.VehiculoProveedor;
import java.time.LocalDate;
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
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        VehiculoProveedor elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<VehiculoProveedor> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<VehiculoProveedor> listarPorAlias(String alias) {
        return elementoDAO.findByAliasContaining(alias);
    }
    
    //Obtiene una lista por alias filtro remolque
    public List<VehiculoProveedor> listarPorAliasFiltroRemolque(String alias) {
        return elementoDAO.findByAliasContainingAndTipoVehiculo_EsRemolqueTrue(alias);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public VehiculoProveedor agregar(VehiculoProveedor elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaAlta(LocalDate.now());
        elementoDAO.saveAndFlush(elemento);
        elemento.setAlias(elemento.getDominio());
        return elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(VehiculoProveedor elemento) {
        elemento = formatearStrings(elemento);
        elemento.setFechaUltimaMod(LocalDate.now());
        elemento.setAlias(elemento.getDominio());
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(VehiculoProveedor elemento) {
        elementoDAO.delete(elemento);
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
