package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.constant.Funcion;
import ar.com.wecoode.jitws.dao.IOrigenDestinoDAO;
import ar.com.wecoode.jitws.dao.IProvinciaDAO;
import ar.com.wecoode.jitws.model.OrigenDestino;
import ar.com.wecoode.jitws.model.Provincia;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio OrigenDestino
 * @author blas
 */

@Service
public class OrigenDestinoService {
    
    //Define la referencia al dao
    @Autowired
    IOrigenDestinoDAO elementoDAO;
    
    //Define la referencia al dao provincia
    @Autowired
    IProvinciaDAO provinciaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrigenDestino elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<OrigenDestino> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<OrigenDestino> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene por provincia
    public List<OrigenDestino> listarPorProvincia(int idProvincia) {
        //Obtiene la provincia por id
        Optional<Provincia> provincia = provinciaDAO.findById(idProvincia);
        return elementoDAO.findByProvincia(provincia);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public OrigenDestino agregar(OrigenDestino elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(OrigenDestino elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(OrigenDestino elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private OrigenDestino formatearStrings(OrigenDestino elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    }
    
}
