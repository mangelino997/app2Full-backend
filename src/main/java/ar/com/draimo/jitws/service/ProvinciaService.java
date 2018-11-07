package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IProvinciaDAO;
import ar.com.draimo.jitws.dao.IPaisDAO;
import ar.com.draimo.jitws.model.Provincia;
import ar.com.draimo.jitws.model.Pais;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Provincia
 * @author blas
 */

@Service
public class ProvinciaService {

    //Define la referencia al dao
    @Autowired
    IProvinciaDAO elementoDAO;
    
    //Define la referencia al dao pais
    @Autowired
    IPaisDAO paisDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Provincia elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Provincia> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Provincia> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene una lista por pais
    public List<Provincia> listarPorPais(int id) {
        Optional<Pais> elemento = paisDAO.findById(id);
        return elementoDAO.findByPais(elemento);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Provincia agregar(Provincia elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Provincia elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Provincia elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Provincia formatearStrings(Provincia elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        if(elemento.getCodigoAfip() != null) {
            elemento.setCodigoAfip(elemento.getCodigoAfip().trim());
        }
        return elemento;
    }

}
