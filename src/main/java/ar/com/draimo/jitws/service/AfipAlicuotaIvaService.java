package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.model.AfipAlicuotaIva;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.com.draimo.jitws.dao.IAfipAlicuotaIvaDAO;

/**
 * Servicio AfipAlicuotaIva
 * @author blas
 */

@Service
public class AfipAlicuotaIvaService {
    
    //Define la referencia al dao
    @Autowired
    IAfipAlicuotaIvaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipAlicuotaIva elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<AfipAlicuotaIva> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista de activas
    public List<AfipAlicuotaIva> listarActivas() {
        return elementoDAO.findByEstaActivaTrue();
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipAlicuotaIva agregar(AfipAlicuotaIva elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipAlicuotaIva elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los string
    private AfipAlicuotaIva formatearStrings(AfipAlicuotaIva elemento) {
        elemento.setCodigoAfip(elemento.getCodigoAfip().trim());
        return elemento;
    }
    
}