package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.ILocalidadDAO;
import ar.com.draimo.jitws.dao.IProvinciaDAO;
import ar.com.draimo.jitws.model.Localidad;
import ar.com.draimo.jitws.model.Provincia;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Localidad
 * @author blas
 */

@Service
public class LocalidadService {

    //Define la referencia al dao
    @Autowired
    ILocalidadDAO elementoDAO;
    
    //Define la referencia al dao provincia
    @Autowired
    IProvinciaDAO provinciaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Localidad elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene una lista completa
    public List<Localidad> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Localidad> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreStartingWith(nombre);
        }
    }
    
    //Obtiene una lista por provincia
    public List<Localidad> listarPorProvincia(int idProvincia) {
        Optional<Provincia> provincia = provinciaDAO.findById(idProvincia);
        return elementoDAO.findByProvincia(provincia);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Localidad agregar(Localidad elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Localidad elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Localidad elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Localidad formatearStrings(Localidad elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        elemento.setCodigoPostal(elemento.getCodigoPostal().trim());
        return elemento;
    }

}
