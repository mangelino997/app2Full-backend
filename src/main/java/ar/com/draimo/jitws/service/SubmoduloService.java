//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IModuloDAO;
import ar.com.draimo.jitws.dao.ISubmoduloDAO;
import ar.com.draimo.jitws.dto.GenericoDTO;
import ar.com.draimo.jitws.model.Submodulo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Submodulo
 *
 * @author blas
 */
@Service
public class SubmoduloService {

    //Define la referencia al dao
    @Autowired
    ISubmoduloDAO elementoDAO;

    //Define la referencia al dao modulo
    @Autowired
    IModuloDAO moduloDAO;

    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;

    //Obtiene listas necesarias para inicializar el componente (front)
    public GenericoDTO inicializar(int idRol, int idSubopcion) {
        GenericoDTO elemento = new GenericoDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setModulos(moduloDAO.findAll());
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Submodulo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<Submodulo> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<Submodulo> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Obtiene una lista por modulo
    public List<Submodulo> listarPorModulo(int idModulo) {
        return elementoDAO.findByModulo(moduloDAO.findById(idModulo));
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Submodulo agregar(Submodulo elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Submodulo elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private Submodulo formatearStrings(Submodulo elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
