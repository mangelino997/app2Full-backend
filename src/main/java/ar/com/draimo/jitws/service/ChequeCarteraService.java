//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IChequeCarteraDAO;
import ar.com.draimo.jitws.dao.IBancoDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.model.ChequeCartera;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de ChequeCartera
 * @author blas
 */

@Service
public class ChequeCarteraService {

    //Define la referencia al DAO
    @Autowired
    IChequeCarteraDAO elementoDAO;

    //Define la referencia al DAO de banco
    @Autowired
    IBancoDAO bancoDAO;
    
    //Define la referencia al DAO de empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        ChequeCartera elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<ChequeCartera> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por Empresa 
    public List<ChequeCartera> listarPorEmpresa(int idEmpresa) {
            return elementoDAO.findByEmpresa(empresaDAO.findById(idEmpresa).get());
    }
    
    //Obtiene una lista por Banco
    public List<ChequeCartera> listarPorBanco(int idBanco) {
            return elementoDAO.findByBanco(bancoDAO.findById(idBanco).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public ChequeCartera agregar(ChequeCartera elemento) throws Exception {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(ChequeCartera elemento) throws Exception {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}