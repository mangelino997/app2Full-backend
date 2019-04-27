package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ITramoDAO;
import ar.com.draimo.jitws.model.Tramo;
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
    
    //Obtiene una lista por destino
    public List<Tramo> listarPorDestino(String nombre) {
        return elementoDAO.findByDestino_NombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Tramo agregar(Tramo elemento) {
        elemento = formatearStrings(elemento);
        if(elemento.getRutaAlternativa() == null) {
            elemento.setRutaAlternativa("-");
        }
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
            elemento.setRutaAlternativa(elemento.getRutaAlternativa().trim());
        }
        return elemento;
    }

}
