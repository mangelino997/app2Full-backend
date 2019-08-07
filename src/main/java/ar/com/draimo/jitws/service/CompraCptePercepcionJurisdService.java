package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICompraCptePercepcionJurisdDAO;
import ar.com.draimo.jitws.model.CompraCptePercepcionJurisd;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */

@Service
public class CompraCptePercepcionJurisdService {

    @Autowired
    ICompraCptePercepcionJurisdDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CompraCptePercepcionJurisd elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<CompraCptePercepcionJurisd> listar() {
        return elementoDAO.findAll();
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CompraCptePercepcionJurisd agregar(CompraCptePercepcionJurisd elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CompraCptePercepcionJurisd elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

}
