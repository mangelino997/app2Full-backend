//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IAfipActividadDAO;
import ar.com.draimo.jitws.model.AfipActividad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de AfipActividad
 *
 * @author blas
 */
@Service
public class AfipActividadService {

    //Define el dao

    //inyectamos la dependencia a un Bean (repository) con Autowired
    @Autowired
    IAfipActividadDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipActividad elemento = elementoDAO.findTopByOrderByIdDesc();
        return (elemento != null ? elemento.getId() + 1 : 1);
    }

    //Obtiene la lista completa
    public List<AfipActividad> listar() {
        return elementoDAO.findByOrderByCodigoAfipAsc();
    }

    //Obtiene una lista por alias
    public List<AfipActividad> listarPorAlias(String alias) {
        return alias.equals("*") ? elementoDAO.findByOrderByCodigoAfipAsc()
                : elementoDAO.findByAliasContaining(alias);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipActividad agregar(AfipActividad elemento) {
        formatearString(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipActividad elemento) {
        establecerAlias(elemento);
    }

    //Establece el alias de un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipActividad establecerAlias(AfipActividad elemento) {
        elemento.setAlias(elemento.getCodigoAfip()
                + " - " + elemento.getNombre());
        return elementoDAO.save(elemento);
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los string 
    private AfipActividad formatearString(AfipActividad elemento) {
        elemento.setNombre(Funcion.convertirATitulo(elemento.getNombre().trim()));
        return elemento;
    }

}
