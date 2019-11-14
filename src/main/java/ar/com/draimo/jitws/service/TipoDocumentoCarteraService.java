//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.ITipoDocumentoCarteraDAO;
import ar.com.draimo.jitws.model.TipoDocumentoCartera;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de TipoDocumentoCartera
 *
 * @author blas
 */
@Service
public class TipoDocumentoCarteraService {

    //Define el dao
    @Autowired
    ITipoDocumentoCarteraDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        TipoDocumentoCartera elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<TipoDocumentoCartera> listar() {
        return elementoDAO.findAll();
    }

    //Obtiene una lista por Nombre
    public List<TipoDocumentoCartera> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findAll()
                : elementoDAO.findByNombreContaining(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public TipoDocumentoCartera agregar(TipoDocumentoCartera elemento) {
        formatearString(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(TipoDocumentoCartera elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los string 
    private TipoDocumentoCartera formatearString(TipoDocumentoCartera elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    }

}
