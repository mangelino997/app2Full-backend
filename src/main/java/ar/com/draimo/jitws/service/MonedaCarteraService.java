//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.IMonedaCarteraDAO;
import ar.com.draimo.jitws.dao.IMonedaDAO;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.model.MonedaCartera;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de MonedaCartera
 * @author blas
 */

@Service
public class MonedaCarteraService {

    //Define la referencia al DAO
    @Autowired
    IMonedaCarteraDAO elementoDAO;

    //Define la referencia al DAO de moneda
    @Autowired
    IMonedaDAO monedaDAO;
    
    //Define la referencia al DAO de empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        MonedaCartera elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<MonedaCartera> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por Empresa 
    public List<MonedaCartera> listarPorEmpresa(int idEmpresa) {
            return elementoDAO.findByEmpresa(empresaDAO.findById(idEmpresa).get());
    }
    
    //Obtiene una lista por moneda
    public List<MonedaCartera> listarPorMoneda(int idMoneda) {
            return elementoDAO.findByMoneda(monedaDAO.findById(idMoneda).get());
    }

    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public MonedaCartera agregar(MonedaCartera elemento) throws Exception {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(MonedaCartera elemento) throws Exception {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}