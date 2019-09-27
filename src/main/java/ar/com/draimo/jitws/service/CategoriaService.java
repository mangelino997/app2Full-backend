//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICategoriaDAO;
import ar.com.draimo.jitws.exception.MensajeRespuesta;
import ar.com.draimo.jitws.model.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Categoria
 * @author blas
 */

@Service
public class CategoriaService {
    
    //Define la referencia al dao
    @Autowired
    ICategoriaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        Categoria elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<Categoria> listar() {
        return elementoDAO.findByOrderByNombreAsc();
    }
    
    //Obtiene una lista por nombre
    public List<Categoria> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findByOrderByNombreAsc();
        } else {
            return elementoDAO.findByNombreContainingOrderByNombreAsc(nombre);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public Categoria agregar(Categoria elemento) throws Exception {
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Categoria elemento) throws Exception {
        elemento = formatearStrings(elemento);
        controlarLongitud(elemento);
        elementoDAO.save(elemento);
    }
    
    //Control de longitud
    private void controlarLongitud(Categoria elemento){
        String dLab = String.valueOf(elemento.getDiasLaborables());
        if (dLab.length()>2 || Integer.valueOf(dLab)> 31) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD+" DIAS LABORABLES");
        }
        //Obtiene longitud de horas laborables, si supera 24 retorna error
        String hLab = String.valueOf(elemento.getHorasLaborables());
        if (hLab.length()>2 || Integer.valueOf(hLab)>24) {
            throw new DataIntegrityViolationException(MensajeRespuesta.LONGITUD+" HORAS LABORABLES");
        }
    }
    
    //Eliminar un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private Categoria formatearStrings(Categoria elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }
    
}