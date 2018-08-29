package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IMarcaVehiculoDAO;
import ar.com.wecoode.jitws.model.MarcaVehiculo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Marca Vehiculo
 * @author blas
 */

@Service
public class MarcaVehiculoService {
    
    //Define la referencia al dao
    @Autowired
    IMarcaVehiculoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<MarcaVehiculo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<MarcaVehiculo> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Agrega un registro
    public void agregar(MarcaVehiculo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(MarcaVehiculo elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(MarcaVehiculo elemento) {
        elementoDAO.delete(elemento);
    }
    
}
