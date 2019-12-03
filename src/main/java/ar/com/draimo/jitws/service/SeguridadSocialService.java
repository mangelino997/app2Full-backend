//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ISeguridadSocialDAO;
import ar.com.draimo.jitws.model.SeguridadSocial;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio SeguridadSocial
 *
 * @author blas
 */
@Service
public class SeguridadSocialService {

    //Define la referencia al dao
    @Autowired
    ISeguridadSocialDAO elementoDAO;

    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        SeguridadSocial elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId() + 1 : 1;
    }

    //Obtiene la lista completa
    public List<SeguridadSocial> listar() {
        return elementoDAO.findByOrderByNombreAsc();
    }

    //Obtiene una lista por nombre
    public List<SeguridadSocial> listarPorNombre(String nombre) {
        return nombre.equals("*") ? elementoDAO.findByOrderByNombreAsc()
                : elementoDAO.findByNombreContainingOrderByNombreAsc(nombre);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public SeguridadSocial agregar(SeguridadSocial elemento) {
        return elementoDAO.saveAndFlush(formatearStrings(elemento));
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(SeguridadSocial elemento) {
        elementoDAO.save(formatearStrings(elemento));
    }

    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }

    //Formatea los strings
    private SeguridadSocial formatearStrings(SeguridadSocial elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        if (elemento.getSitioWeb() != null) {
            elemento.setSitioWeb(elemento.getSitioWeb().trim().toLowerCase());
        }
        return elemento;
    }

}