//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IProveedorDAO;
import ar.com.draimo.jitws.dao.IProveedorCuentaBancariaDAO;
import ar.com.draimo.jitws.model.ProveedorCuentaBancaria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio c
 *
 * @author blas
 */
@Service
public class ProveedorCuentaBancariaService {

    //Define la referencia al dao 
    @Autowired
    IProveedorCuentaBancariaDAO elementoDAO;

    //Define la referencia al dao proveedor
    @Autowired
    IProveedorDAO proveedorDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ProveedorCuentaBancaria elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<ProveedorCuentaBancaria> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por Cliente
    public List<ProveedorCuentaBancaria> listarPorProveedor(int idProveedor) {
        return elementoDAO.findByProveedor(proveedorDAO.findById(idProveedor).get());
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ProveedorCuentaBancaria agregar(ProveedorCuentaBancaria elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public ProveedorCuentaBancaria actualizar(ProveedorCuentaBancaria elemento) {
        return elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }

}