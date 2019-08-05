package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.dao.ICompaniaSeguroDAO;
import ar.com.draimo.jitws.model.CompaniaSeguro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Compania Seguro
 * @author blas
 */

@Service
public class CompaniaSeguroService {
    
    //Define la referencia al dao
    @Autowired
    ICompaniaSeguroDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CompaniaSeguro elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<CompaniaSeguro> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por nombre
    public List<CompaniaSeguro> listarPorNombre(String nombre) {
        if(nombre.equals("***")){
            return elementoDAO.findAll();
        }else {
            return elementoDAO.findByNombreContaining(nombre);
        }
    }
    
    //Obtiene una lista de companias de seguro por empresa (Consulta CompaniaSeguroPoliza)
    public List<CompaniaSeguro> listarPorEmpresa(int idEmpresa) {
        return elementoDAO.listarPorEmpresa(idEmpresa);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CompaniaSeguro agregar(CompaniaSeguro elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CompaniaSeguro elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(int elemento) {
        elementoDAO.deleteById(elemento);
    }
    
    //Formatea los strings
    private CompaniaSeguro formatearStrings(CompaniaSeguro elemento) {
        elemento.setNombre(elemento.getNombre().trim().toUpperCase());
        return elemento;
    }
    
}
