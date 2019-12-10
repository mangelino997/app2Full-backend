//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ILocalidadDAO;
import ar.com.draimo.jitws.dao.IProvinciaDAO;
import ar.com.draimo.jitws.dto.InitLocalidadDTO;
import ar.com.draimo.jitws.model.Localidad;
import java.util.List;
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
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public InitLocalidadDTO inicializar(int idRol, int idSubopcion) {
        InitLocalidadDTO elemento = new InitLocalidadDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setProvincias(provinciaDAO.findAll());
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Localidad elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene una lista completa
    public List<Localidad> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Localidad> listarPorNombre(String nombre) {
        return nombre.equals("*")?elementoDAO.findAll():
            elementoDAO.findByNombreContaining(nombre);
    }
    
    //Obtiene una lista por provincia
    public List<Localidad> listarPorProvincia(int idProvincia) {
        return idProvincia != 0 ? 
                elementoDAO.findByProvinciaOrderByNombreAsc(provinciaDAO.findById(idProvincia).get()) : 
                elementoDAO.findAllByOrderByNombreAsc();
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
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private Localidad formatearStrings(Localidad elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        if(elemento.getCodigoPostal() != null) {
            elemento.setCodigoPostal(elemento.getCodigoPostal().trim());
        }
        return elemento;
    }

}