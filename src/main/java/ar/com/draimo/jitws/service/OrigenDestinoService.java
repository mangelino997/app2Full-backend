//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IOrigenDestinoDAO;
import ar.com.draimo.jitws.dao.IProvinciaDAO;
import ar.com.draimo.jitws.dto.GenericoDTO;
import ar.com.draimo.jitws.model.OrigenDestino;
import ar.com.draimo.jitws.model.Provincia;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio OrigenDestino
 *
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
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public GenericoDTO inicializar(int idRol, int idSubopcion) {
        GenericoDTO elemento = new GenericoDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        OrigenDestino elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<OrigenDestino> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<OrigenDestino> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Obtiene un listado por provincia
    public List<OrigenDestino> listarPorProvincia(int idProvincia) {
        //Obtiene la provincia por id
        Optional<Provincia> provincia = provinciaDAO.findById(idProvincia);
        return elementoDAO.findByProvincia(provincia);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public OrigenDestino agregar(OrigenDestino elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(OrigenDestino elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private OrigenDestino formatearStrings(OrigenDestino elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
