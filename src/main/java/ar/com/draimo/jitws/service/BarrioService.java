//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IBarrioDAO;
import ar.com.draimo.jitws.dto.GenericoDTO;
import ar.com.draimo.jitws.dto.initBarrioDTO;
import ar.com.draimo.jitws.model.Barrio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de Barrio
 * @author blas
 */

@Service
public class BarrioService {

    //Referencia al DAO
    @Autowired
    IBarrioDAO elementoDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Referencia al service de zonas
    @Autowired
    ZonaService zonaService;
    
    //Obtiene listas necesarias para inicializar el componente (front)
    public initBarrioDTO inicializar(int idRol, int idsubpcion) {
        initBarrioDTO elemento = new initBarrioDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idsubpcion));
        elemento.setUltimoId(obtenerSiguienteId());
        elemento.setZonas(zonaService.listar());
        return elemento;
    }
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Barrio elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Barrio> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<Barrio> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll():
            elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Barrio agregar(Barrio elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Barrio elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private Barrio formatearStrings(Barrio elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}