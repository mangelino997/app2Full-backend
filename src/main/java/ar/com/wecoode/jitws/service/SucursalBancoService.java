package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.ISucursalBancoDAO;
import ar.com.wecoode.jitws.model.SucursalBanco;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        SucursalBanco elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<SucursalBanco> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<SucursalBanco> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene una lista por nombre de banco
    public List<SucursalBanco> listarPorNombreBanco(String nombreBanco) {
        return elementoDAO.findByBanco_NombreContaining(nombreBanco);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SucursalBanco agregar(SucursalBanco elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SucursalBanco elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(SucursalBanco elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private SucursalBanco formatearStrings(SucursalBanco elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    }
    
}
