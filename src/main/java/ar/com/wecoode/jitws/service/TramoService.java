package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.ITramoDAO;
import ar.com.wecoode.jitws.model.Tramo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Tramo
 * @author blas
 */

@Service
public class TramoService {

    //Define la referencia al dao
    @Autowired
    ITramoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Tramo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Tramo> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por origen
    public List<Tramo> listarPorOrigen(String nombre) {
        return elementoDAO.findByOrigen_NombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Tramo agregar(Tramo elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Tramo elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Tramo elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Tramo formatearStrings(Tramo elemento) {
        if(elemento.getRutaAlternativa() != null) {
            elemento.setRutaAlternativa(Funcion.primerLetraAMayuscula(elemento.getRutaAlternativa().trim()));
        }
        return elemento;
    }

}
