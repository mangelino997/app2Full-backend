package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IEscalaTarifaDAO;
import ar.com.draimo.jitws.model.EscalaTarifa;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio EscalaTarifa
 * @author blas
 */

@Service
public class EscalaTarifaService {
    
    //Define la referencia al dao
    @Autowired
    IEscalaTarifaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        EscalaTarifa elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento!=null?elemento.getId()+1:1);
    }
    
    //Obtiene la lista completa
    public List<EscalaTarifa> listar() {
        List<EscalaTarifa> lista = elementoDAO.findAll();
        lista.sort(Comparator.comparing(EscalaTarifa::getValor));
        return lista;
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public EscalaTarifa agregar(EscalaTarifa elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(EscalaTarifa elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int id) {
        elementoDAO.deleteById(id);
    }
    
}
