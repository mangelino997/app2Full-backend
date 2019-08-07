package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IObraSocialDAO;
import ar.com.draimo.jitws.model.ObraSocial;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio ObraSocial
 * @author blas
 */

@Service
public class ObraSocialService {
    
    //Define la referencia al dao
    @Autowired
    IObraSocialDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ObraSocial elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ObraSocial> listar() {
        return elementoDAO.findByOrderByCodigoAfipAsc();
    }
    
    //Obtiene una lista por nombre
    public List<ObraSocial> listarPorAlias(String alias) {
        if(alias.equals("***")) {
            return elementoDAO.findByOrderByCodigoAfipAsc();
        } else {
            return elementoDAO.findByAliasContainingOrderByNombreAsc(alias);
        }
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ObraSocial agregar(ObraSocial elemento) {
        elemento = elementoDAO.saveAndFlush(elemento);
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ObraSocial elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private ObraSocial formatearStrings(ObraSocial elemento) {
        elemento.setNombre(elemento.getNombre().trim());
        elemento.setCodigoAfip(elemento.getCodigoAfip().trim());
        if(elemento.getSitioWeb() != null) {
            elemento.setSitioWeb(elemento.getSitioWeb().trim().toLowerCase());
        }
        elemento.setAlias(elemento.getCodigoAfip()+ " - " + elemento.getNombre());
        return elemento;
    }
    
}
