package ar.com.draimo.jitws.service;
import ar.com.draimo.jitws.dao.IGrupoCuentaContableDAO;
import ar.com.draimo.jitws.dao.ITipoCuentaContableDAO;
import ar.com.draimo.jitws.model.GrupoCuentaContable;
import ar.com.draimo.jitws.model.TipoCuentaContable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio GrupoCuentaContable
 * @author blas
 */

@Service
public class GrupoCuentaContableService {

    //Define la referencia al dao
    @Autowired
    IGrupoCuentaContableDAO elementoDAO;
    
    //Define la referencia al dao pais
    @Autowired
    ITipoCuentaContableDAO tipoCuentaContableDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        GrupoCuentaContable elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<GrupoCuentaContable> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<GrupoCuentaContable> listarPorNombre(String nombre) {
        if(nombre.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene una lista por pais
    public List<GrupoCuentaContable> listarPorTipoCuentaContable(int id) {
        TipoCuentaContable elemento = tipoCuentaContableDAO.findById(id).get();
        return elementoDAO.findByTipoCuentaContable(elemento);
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public GrupoCuentaContable agregar(GrupoCuentaContable elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(GrupoCuentaContable elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(GrupoCuentaContable elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private GrupoCuentaContable formatearStrings(GrupoCuentaContable elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        return elemento;
    }

}
