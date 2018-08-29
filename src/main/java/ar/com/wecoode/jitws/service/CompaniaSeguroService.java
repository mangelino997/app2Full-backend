package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ICompaniaSeguroDAO;
import ar.com.wecoode.jitws.model.CompaniaSeguro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Compania Seguro
 * @author blas
 */

@Service
public class CompaniaSeguroService {
    
    //Define la referencia al dao
    @Autowired
    ICompaniaSeguroDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<CompaniaSeguro> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<CompaniaSeguro> listarPorNombre(String nombre) {
        return elementoDAO.findByNombreContaining(nombre);
    }
    
    //Agrega un registro
    public void agregar(CompaniaSeguro elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(CompaniaSeguro elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(CompaniaSeguro elemento) {
        elementoDAO.delete(elemento);
    }
    
}
