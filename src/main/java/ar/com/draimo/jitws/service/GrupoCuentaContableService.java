//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IGrupoCuentaContableDAO;
import ar.com.draimo.jitws.model.GrupoCuentaContable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio GrupoCuentaContable
 * @author blas
 */

@Service
public class GrupoCuentaContableService {

    //Referencia al dao
    @Autowired
    IGrupoCuentaContableDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        GrupoCuentaContable elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<GrupoCuentaContable> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public GrupoCuentaContable agregar(GrupoCuentaContable elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(GrupoCuentaContable elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}