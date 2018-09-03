package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ICompaniaSeguroPolizaDAO;
import ar.com.wecoode.jitws.model.CompaniaSeguroPoliza;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Compania Seguro Poliza
 * @author blas
 */

@Service
public class CompaniaSeguroPolizaService {
    
    //Define la referencia al dao
    @Autowired
    ICompaniaSeguroPolizaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<CompaniaSeguroPoliza> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    public void agregar(CompaniaSeguroPoliza elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(CompaniaSeguroPoliza elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(CompaniaSeguroPoliza elemento) {
        elementoDAO.delete(elemento);
    }
    
}
