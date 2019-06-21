package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IPdfDAO;
import ar.com.draimo.jitws.model.Pdf;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author blas
 */

@Service
public class PdfService {

    @Autowired
    IPdfDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Pdf elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene por id
    public Pdf obtenerPorId(int id) {
        return elementoDAO.findById(id).get();
    }
    
    //Obtiene una lista por nombre
    public List<Pdf> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Pdf agregar(Pdf elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Pdf elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Pdf elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Pdf formatearStrings(Pdf elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
