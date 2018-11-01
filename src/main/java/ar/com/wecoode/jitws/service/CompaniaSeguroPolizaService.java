package ar.com.wecoode.jitws.service;

import ar.com.wecoode.jitws.dao.ICompaniaSeguroPolizaDAO;
import ar.com.wecoode.jitws.dao.IEmpresaDAO;
import ar.com.wecoode.jitws.model.CompaniaSeguroPoliza;
import ar.com.wecoode.jitws.model.Empresa;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio Compania Seguro Poliza
 * @author blas
 */

@Service
public class CompaniaSeguroPolizaService {
    
    //Define la referencia al dao
    @Autowired
    ICompaniaSeguroPolizaDAO elementoDAO;
    
    //Define la referencia al dao empresa
    @Autowired
    IEmpresaDAO empresaDAO;
    
    //Obtiene el siguiente id
    public int obtenerSiguienteId() {
        CompaniaSeguroPoliza elemento = elementoDAO.findTopByOrderByIdDesc();
        return elemento != null ? elemento.getId()+1 : 1;
    }
    
    //Obtiene la lista completa
    public List<CompaniaSeguroPoliza> listar() {
        return elementoDAO.findAll();
    }
    
    //Obtiene una lista por empresa
    public List<CompaniaSeguroPoliza> listarPorEmpresa(int id) {
        Optional<Empresa> empresa = empresaDAO.findById(id);
        return elementoDAO.findByEmpresa(empresa);
    }
    
    //Agrega un registro
    @Transactional(rollbackFor = Exception.class)
    public CompaniaSeguroPoliza agregar(CompaniaSeguroPoliza elemento) {
        return elementoDAO.save(elemento);
    }
    
    //Actualiza un registro
    @Transactional(rollbackFor = Exception.class)
    public void actualizar(CompaniaSeguroPoliza elemento) {
        elementoDAO.save(elemento);
    }
    
    //Elimina un registro
    @Transactional(rollbackFor = Exception.class)
    public void eliminar(CompaniaSeguroPoliza elemento) {
        elementoDAO.delete(elemento);
    }
    
}
