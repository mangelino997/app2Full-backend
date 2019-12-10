//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.AfipCondicionIva;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IAfipCondicionIvaDAO;
import ar.com.draimo.jitws.dto.GenericoDTO;

/**
 * Servicio Afip Condicion Iva
 *
 * @author blas
 */
@Service
public class AfipCondicionIvaService {

    //Define la referencia al dao
    @Autowired
    IAfipCondicionIvaDAO elementoDAO;
    
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
        AfipCondicionIva elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<AfipCondicionIva> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por nombre
    public List<AfipCondicionIva> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipCondicionIva agregar(AfipCondicionIva elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipCondicionIva elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private AfipCondicionIva formatearStrings(AfipCondicionIva elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
