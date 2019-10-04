//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IAfipCondicionDAO;
import ar.com.draimo.jitws.model.AfipCondicion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipCondicion
 *
 * @author blas
 */
@Service
public class AfipCondicionService {

    //Define el dao
    @Autowired
    IAfipCondicionDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipCondicion elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<AfipCondicion> listar() {
        return elementoDAO.findByOrderByCodigoAfipAsc();
    }

    //Obtiene una lista por alias
    public List<AfipCondicion> listarPorAlias(String alias) {
        return alias.equals("***") ? elementoDAO.findByOrderByCodigoAfipAsc()
                : elementoDAO.findByAliasContaining(alias);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipCondicion agregar(AfipCondicion elemento) {
        formatearString(elemento);
        return elementoDAO.save(elemento);
    }

    //Actualiza el registro estableciendo el alias
    @Transactional(rollbackFor = Exception.class)
    public void establecerAlias(AfipCondicion elemento) {
        elemento.setAlias(elemento.getCodigoAfip() + " - " + elemento.getNombre());
        elementoDAO.save(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipCondicion elemento) {
        formatearString(elemento);
        establecerAlias(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los string 
    private AfipCondicion formatearString(AfipCondicion elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    }

}
