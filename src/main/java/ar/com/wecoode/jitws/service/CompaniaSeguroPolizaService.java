package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ICompaniaSeguroPolizaDAO;
import ar.com.wecoode.jitws.model.CompaniaSeguroPoliza;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public void agregar(CompaniaSeguroPoliza elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CompaniaSeguroPoliza elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(CompaniaSeguroPoliza elemento) {
        elementoDAO.delete(elemento);
    }
    
}
