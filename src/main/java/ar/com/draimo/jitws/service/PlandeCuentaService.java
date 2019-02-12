package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPlandeCuentaDAO;
import ar.com.draimo.jitws.model.PlandeCuenta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio PlandeCuenta
 * @author blas
 */

@Service
public class PlandeCuentaService {
    
    //Define la referencia al dao
    @Autowired
    IPlandeCuentaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        PlandeCuenta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<PlandeCuenta> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<PlandeCuenta> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene un listado por grupocuentacontable y estaactivo
    public List<PlandeCuenta> listarGrupoActivo(int idEmpresa) {
        return elementoDAO.listarGrupoActivo(idEmpresa);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public PlandeCuenta agregar(PlandeCuenta elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(PlandeCuenta elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(PlandeCuenta elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private PlandeCuenta formatearStrings(PlandeCuenta elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}
