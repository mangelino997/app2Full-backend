package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ISucursalBancoDAO;
import ar.com.wecoode.jitws.model.SucursalBanco;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Sucursal Banco
 * @author blas
 */

@Service
public class SucursalBancoService {
    
    //Define la referencia al dao
    @Autowired
    ISucursalBancoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<SucursalBanco> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<SucursalBanco> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }
    
    //Obtiene una lista por nombre de banco
    public List<SucursalBanco> listarPorNombreBanco(String nombreBanco) {
        return elementoDAO.findByBanco_NombreContaining(nombreBanco);
    }
    
    //Agrega un registro
    public void agregar(SucursalBanco elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(SucursalBanco elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(SucursalBanco elemento) {
        elementoDAO.delete(elemento);
    }
    
}
