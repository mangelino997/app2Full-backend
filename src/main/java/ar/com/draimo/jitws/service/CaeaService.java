package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICaeaDAO;
import ar.com.draimo.jitws.model.Caea;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Caea
 * @author blas
 */

@Service
public class CaeaService {
    
    //Define la referencia al dao
    @Autowired
    ICaeaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Caea elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<Caea> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Caea agregar(Caea elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Caea elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Caea elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Caea formatearStrings(Caea elemento) {
        elemento.setNumeroCAEA(elemento.getNumeroCAEA().trim());
        return elemento;
    }
    
}
