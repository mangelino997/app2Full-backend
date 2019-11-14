//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IChequeraItemDAO;
import ar.com.draimo.jitws.dao.ILibroBancoDAO;
import ar.com.draimo.jitws.dao.IChequeraDAO;
import ar.com.draimo.jitws.model.ChequeraItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de ChequeraItem
 * @author blas
 */

@Service
public class ChequeraItemService {

    //Define la referencia al DAO
    @Autowired
    IChequeraItemDAO elementoDAO;

    //Define la referencia al DAO de banco
    @Autowired
    ILibroBancoDAO libroBancoDAO;
    
    //Define la referencia al DAO de empresa
    @Autowired
    IChequeraDAO chequeraDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ChequeraItem elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ChequeraItem> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por Empresa 
    public List<ChequeraItem> listarPorChequera(int idEmpresa) {
            return elementoDAO.findByChequera(chequeraDAO.findById(idEmpresa).get());
    }
    
    //Obtiene una lista por Banco
    public List<ChequeraItem> listarPorLibroBanco(int idBanco) {
            return elementoDAO.findByLibroBanco(libroBancoDAO.findById(idBanco).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ChequeraItem agregar(ChequeraItem elemento) throws Exception {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ChequeraItem elemento) throws Exception {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}