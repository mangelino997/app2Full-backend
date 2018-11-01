package ar.com.draimo.jitws.service;

import ar.com.draimo.jitws.constant.Funcion;
import ar.com.draimo.jitws.dao.IEmpresaDAO;
import ar.com.draimo.jitws.model.Empresa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Empresa elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento.getId()+1;
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
    @Transactional(rollbackFor = Exception.class)
    public Empresa agregar(Empresa elemento) {
        elemento = formatearStrings(elemento);
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(Empresa elemento) {
        elemento = formatearStrings(elemento);
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(Empresa elemento) {
        elementoDAO.delete(elemento);
    }
    
    //Formatea los strings
    private Empresa formatearStrings(Empresa elemento) {
        elemento.setRazonSocial(Funcion.convertirATitulo(elemento.getRazonSocial().trim()));
        elemento.setDomicilio(Funcion.primerLetraAMayuscula(elemento.getDomicilio().trim()));
        elemento.setCuit(elemento.getCuit().trim());
        elemento.setNumeroIIBB(elemento.getNumeroIIBB().trim());
        elemento.setAbreviatura(elemento.getAbreviatura().trim());
        return elemento;
    }
    
}
