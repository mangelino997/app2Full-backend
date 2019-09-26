//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.AfipAlicuotaGanancia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IAfipAlicuotaGananciaDAO;

/**
 * Servicio AfipAlicuotaGanancia
 * @author blas
 */

@Service
public class AfipAlicuotaGananciaService {
    
    //Define la referencia al dao
    @Autowired
    IAfipAlicuotaGananciaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipAlicuotaGanancia elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<AfipAlicuotaGanancia> listar() {
        return elementoDAO.findAllByOrderByAlicuotaAsc();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipAlicuotaGanancia agregar(AfipAlicuotaGanancia elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipAlicuotaGanancia elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}