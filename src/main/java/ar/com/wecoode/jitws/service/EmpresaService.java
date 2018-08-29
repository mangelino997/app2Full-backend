package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.IEmpresaDAO;
import ar.com.wecoode.jitws.model.Empresa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio Empresa
 * @author blas
 */

@Service
public class EmpresaService {
    
    //Define la referencia al dao
    @Autowired
    IEmpresaDAO elementoDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        return elementoDAO.obtenerSiguienteId();
    }
    
    //Obtiene la lista completa
    public List<Empresa> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene por razon social
    public List<Empresa> listarPorRazonSocial(String razonSocial) {
        if(razonSocial.equals("***")) {
            return elementoDAO.findAll();
        } else {
            return elementoDAO.findByRazonSocialContaining(razonSocial);
        }
    }
    
    //Agrega un registro
    public void agregar(Empresa elemento) {
        elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    public void actualizar(Empresa elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    public void eliminar(Empresa elemento) {
        elementoDAO.delete(elemento);
    }
    
}
