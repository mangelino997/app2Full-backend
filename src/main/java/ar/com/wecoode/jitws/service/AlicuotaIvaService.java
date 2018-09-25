package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IAlicuotaIvaDAO;
import ar.com.wecoode.jitws.model.AlicuotaIva;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AlicuotaIva
 * @author blas
 */

@Service
public class AlicuotaIvaService {
    
    //Define la referencia al dao
    @Autowired
    IAlicuotaIvaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AlicuotaIva elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<AlicuotaIva> listar() {
        return elementoDAO.findAll();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AlicuotaIva agregar(AlicuotaIva elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AlicuotaIva elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(AlicuotaIva elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los string
    private AlicuotaIva formatearStrings(AlicuotaIva elemento) {
        elemento.setCodigoAfip(elemento.getCodigoAfip().trim());
        return elemento;
    }
    
}
