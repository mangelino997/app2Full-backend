//Paquete al que pertenece el servicio
package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ILibroBancoDAO;
import ar.com.draimo.jitws.model.LibroBanco;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de LibroBanco
 * @author blas
 */

@Service
public class LibroBancoService {

    //Define la referencia al DAO
    @Autowired
    ILibroBancoDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        LibroBanco elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<LibroBanco> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por Empresa 
    public List<LibroBanco> listarPorEmpresa(int idEmpresa) {
        return elementoDAO.listarPorEmpresa(idEmpresa);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public LibroBanco agregar(LibroBanco elemento) throws Exception {
        return elementoDAO.saveAndFlush(elemento);
    }

    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(LibroBanco elemento) throws Exception {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
}