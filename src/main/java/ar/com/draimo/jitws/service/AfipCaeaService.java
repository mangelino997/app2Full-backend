package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.AfipCaea;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IAfipCaeaDAO;

/**
 * Servicio AfipCaea
 * @author blas
 */

@Service
public class AfipCaeaService {
    
    //Define la referencia al dao
    @Autowired
    IAfipCaeaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipCaea elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<AfipCaea> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipCaea agregar(AfipCaea elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipCaea elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(AfipCaea elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private AfipCaea formatearStrings(AfipCaea elemento) {
        elemento.setNumeroCAEA(elemento.getNumeroCAEA().trim());
        return elemento;
    }
    
}
