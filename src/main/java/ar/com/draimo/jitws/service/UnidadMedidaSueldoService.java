//Paquete al que pertenece el servicio

package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IUnidadMedidaSueldoDAO;
import ar.com.draimo.jitws.dto.GenericoDTO;
import ar.com.draimo.jitws.model.UnidadMedidaSueldo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *Servicio de UnidadMedidaSueldo
 * @author marina
 */
@Service
public class UnidadMedidaSueldoService {
    
    //Define la referencia al dao
    @Autowired
    IUnidadMedidaSueldoDAO elementoDAO;
    
    //Referencia al service de subopcionpestania
    @Autowired
    SubopcionPestaniaService subopcionPestaniaService;
    
    //Obtiene listas necesarias para inicializar el componente
    public GenericoDTO inicializar(int idRol, int idSubopcion){
        GenericoDTO elemento = new GenericoDTO();
        elemento.setPestanias(subopcionPestaniaService.listarPestaniasPorRolYSubopcion(idRol, idSubopcion));
        elemento.setUltimoId(obtenerSiguienteId());
        return elemento;
    }
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        UnidadMedidaSueldo elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null? elemento.getId()+1 : 1;
    }
     //Obtiene la lista completa
    public List<UnidadMedidaSueldo> listar() {
        return elementoDAO.findAll();
    }
    //Obtiene una lista por nombre
    public List<UnidadMedidaSueldo> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public UnidadMedidaSueldo agregar(UnidadMedidaSueldo elemento) {
        return elementoDAO.saveAndFlush(elemento);
    }
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(UnidadMedidaSueldo elemento) {
        elementoDAO.save(elemento);
    }
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
}
