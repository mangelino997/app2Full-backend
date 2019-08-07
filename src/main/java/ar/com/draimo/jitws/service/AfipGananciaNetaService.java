package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IAfipAlicuotaGananciaDAO;
import ar.com.draimo.jitws.dao.IAfipDeduccionPersonalDAO;
import ar.com.draimo.jitws.dao.IAfipTipoBeneficioDAO;
import ar.com.draimo.jitws.dao.IAfipGananciaNetaDAO;
import ar.com.draimo.jitws.model.AfipGananciaNeta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio AfipGananciaNeta
 * @author blas
 */

@Service
public class AfipGananciaNetaService {
    
    //Define la referencia al dao
    @Autowired
    IAfipGananciaNetaDAO elementoDAO;
    
    //Define la referencia al dao de afipTipoBeneficio
    @Autowired
    IAfipAlicuotaGananciaDAO gananciaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        AfipGananciaNeta elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
    }
    
    //Obtiene la lista completa
    public List<AfipGananciaNeta> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por idAlicuotaGanancia
    public List<AfipGananciaNeta> listarPorAfipAlicuotaGanancia(int idAlicuotaGanancia) {
        return elementoDAO.findByAfipAlicuotaGanancia(gananciaDAO.findById(idAlicuotaGanancia).get());
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public AfipGananciaNeta agregar(AfipGananciaNeta elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(AfipGananciaNeta elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}
