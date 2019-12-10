//Paquete al  que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IViajeUnidadNegocioDAO;
import ar.com.draimo.jitws.dto.GenericoDTO;
import ar.com.draimo.jitws.model.ViajeUnidadNegocio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ViajeUnidadNegocio
 *
 * @author blas
 */
@Service
public class ViajeUnidadNegocioService {

    //Define la referencia al dao
    @Autowired
    IViajeUnidadNegocioDAO elementoDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene el siguiente id
    public GenericoDTO inicializar(int idRol, int idOpcion) {
        GenericoDTO elemento = new GenericoDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idOpcion));
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ViajeUnidadNegocio elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<ViajeUnidadNegocio> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<ViajeUnidadNegocio> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ViajeUnidadNegocio agregar(ViajeUnidadNegocio elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ViajeUnidadNegocio elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private ViajeUnidadNegocio formatearStrings(ViajeUnidadNegocio elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}