package ar.com.wecode.jitws.service;

import ar.com.wecode.jitws.dao.ISucursalBancoDAO;
import ar.com.wecode.jitws.model.SucursalBanco;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Sucursal Banco
 * @author blas
 */

@Service
public class SucursalBancoService {
    
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
    
}
